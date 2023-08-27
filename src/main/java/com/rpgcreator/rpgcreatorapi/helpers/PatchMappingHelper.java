package com.rpgcreator.rpgcreatorapi.helpers;

import com.rpgcreator.rpgcreatorapi.entities.rpg.Rpg;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

@NoArgsConstructor
public class PatchMappingHelper<Entity> {

    public Optional<Entity> updateSmartly(
            Long entityId,
            String entityIdAttributeName,
            JpaRepository<Entity, Long> repository,
            Object currentEntity,
            Map<String, Object> genericBody
    ) {
        Optional<Entity> genericEntity = repository.findById(entityId);

        genericBody.forEach((propertyName, propertyValue) -> {
            Field field = ReflectionUtils.findField(currentEntity.getClass(), propertyName);

            if (!field.getName().equals(entityIdAttributeName)) {
                field.setAccessible(true);

                if (field.getType() == LocalDate.class) {
                    ReflectionUtils.setField(field, genericEntity.get(), stringToLocalDateConverter(propertyValue.toString()));
                } else {
                    ReflectionUtils.setField(field, genericEntity.get(), propertyValue);
                }

                field.setAccessible(false);
            }
        });

        return genericEntity;
    }

    private LocalDate stringToLocalDateConverter(String stringDate) {
        return LocalDate.parse(stringDate);
    }
}
