package by.artem.spring.listener;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

public class BeforeEntityEvent extends ApplicationEvent {
    @Getter
    private AccessType accessType;
    public BeforeEntityEvent(Object source, AccessType accessType) {
        super(source);
        this.accessType = accessType;
    }
}
