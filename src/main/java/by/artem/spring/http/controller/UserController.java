package by.artem.spring.http.controller;

import by.artem.spring.database.entity.Role;
import by.artem.spring.dto.PageResponse;
import by.artem.spring.dto.UserCreateEditDto;
import by.artem.spring.dto.UserFilter;
import by.artem.spring.dto.UserReadDto;
import by.artem.spring.service.CompanyService;
import by.artem.spring.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

import static by.artem.spring.database.entity.Role.*;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;
    private final CompanyService companyService;

    @PreAuthorize("hasAnyAuthority('ADMIN', 'OPERATOR')")
    @GetMapping
    public String findAll(Model model, UserFilter filter, Pageable pageable) {
//        model.addAttribute("users", userService.findAll());
        Page<UserReadDto> page = userService.findAll(filter, pageable);
        model.addAttribute("users", PageResponse.of(page));
        model.addAttribute("filter", filter);
        return "user/users";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER', 'OPERATOR')")
    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id, Model model,
                           @CurrentSecurityContext SecurityContext securityContext,
                           @AuthenticationPrincipal UserDetails userDetails) {

        return userService.findById(id)
                .map(user -> {
                    if (userDetails.getUsername().contains(user.getUsername()) ||
                            !(userDetails.getAuthorities().contains(USER))) {
                        model.addAttribute("user", user);
                        model.addAttribute("roles", values());
                        model.addAttribute("companies", companyService.findAll());
                        return "user/user";
                    } else {
                        throw new ResponseStatusException(HttpStatus.FORBIDDEN);
                    }
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public String create(@ModelAttribute @Validated UserCreateEditDto user, BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("user", user);
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/users/registration";
        }
        UserReadDto dto = userService.create(user);
        return "redirect:/users/" + dto.getId();
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'OPERATOR', 'USER')")
    @PostMapping("/{id}/update")
    public String update(@PathVariable("id") Long id, @ModelAttribute @Validated UserCreateEditDto user,
                         @AuthenticationPrincipal UserDetails userDetails) {

        return userService.update(id, user)
                .map(it -> {
                    if (userDetails.getUsername().contains(user.getUsername()) ||
                            !(userDetails.getAuthorities().contains(USER))) {
                        return "redirect:/users/{id}";
                    } else {
                        throw new ResponseStatusException(HttpStatus.FORBIDDEN);
                    }
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id,
                         @AuthenticationPrincipal UserDetails userDetails) {

        if (userDetails.getUsername().contains(userService.findById(id).get().getUsername()) ||
                !(userDetails.getAuthorities().contains(USER))) {
            userService.delete(id);
            return "redirect:/users";
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/registration")
    public String registration(Model model, @ModelAttribute("user") UserCreateEditDto user) {
        model.addAttribute("user", user);
        model.addAttribute("roles", values());
        model.addAttribute("companies", companyService.findAll());
        return "user/registration";
    }

}
