package by.artem.spring.listener;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

public class AfterEntityEvent extends ApplicationEvent {
    @Getter
    private AccessType accessType;
    public AfterEntityEvent(Object source, AccessType accessType) {
        super(source);
        this.accessType = accessType;
    }
}
