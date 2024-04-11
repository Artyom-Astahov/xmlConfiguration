package by.artem.spring.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EntityListener {

    @EventListener(condition = "#p0.accessType.name == 'CREATE'")
    public void createEntity(AfterEntityEvent afterEntityEvent){
        log.info("Сообщение после сохранением {}", afterEntityEvent);
    }
    @EventListener(condition = "#p0.accessType.name == 'READ'")
    public void readEntity(AfterEntityEvent afterEntityEvent){
        log.info("Сообщение после чтением {}", afterEntityEvent);
    }
    @EventListener(condition = "#p0.accessType.name == 'UPDATE'")
    public void updateEntity(AfterEntityEvent afterEntityEvent){
        log.info("Сообщение после обновлением {}", afterEntityEvent);
    }
    @EventListener(condition = "#p0.accessType.name == 'DELETE'")
    public void deleteEntity(AfterEntityEvent afterEntityEvent){
        log.info("Сообщение после удалением {}", afterEntityEvent);
    }


    @EventListener(condition = "#p0.accessType.name == 'CREATE'")
    public void createEntity(BeforeEntityEvent beforeEntityEvent){
        log.info("Сообщение перед сохранением {}", beforeEntityEvent);
    }
    @EventListener(condition = "#p0.accessType.name == 'READ'")
    public void readEntity(BeforeEntityEvent beforeEntityEvent){
        log.info("Сообщение перед чтением {}", beforeEntityEvent);
    }
    @EventListener(condition = "#p0.accessType.name == 'UPDATE'")
    public void updateEntity(BeforeEntityEvent beforeEntityEvent){
        log.info("Сообщение перед обновлением {}", beforeEntityEvent);
    }
    @EventListener(condition = "#p0.accessType.name == 'DELETE'")
    public void deleteEntity(BeforeEntityEvent beforeEntityEvent){
        log.info("Сообщение перед удалением {}", beforeEntityEvent);
    }





}
