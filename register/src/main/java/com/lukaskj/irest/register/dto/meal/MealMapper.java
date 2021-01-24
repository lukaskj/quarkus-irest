package com.lukaskj.irest.register.dto.meal;

import com.lukaskj.irest.register.entity.Meal;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "cdi")
public interface MealMapper {
   @Mapping(target = "id", ignore = true)
   @Mapping(target = "createdAt", ignore = true)
   @Mapping(target = "updatedAt", ignore = true)
   @Mapping(target = "restaurant", ignore = true)
   public Meal toMeal(AddMealDTO dto);

   // @Mapping(target = "createdAt", ignore = true)
   // @Mapping(target = "updatedAt", ignore = true)
   @Mapping(target = "createdAt", dateFormat = "dd/MM/yyyy HH:mm:ss")
   @Mapping(target = "updatedAt", dateFormat = "dd/MM/yyyy HH:mm:ss")
   public MealDTO toMealDTO(Meal dto);


   @Mapping(target = "createdAt", ignore = true)
   @Mapping(target = "updatedAt", ignore = true)
   // @Mapping(target = "restaurant", ignore = true)
   public void toMeal(UpdateMealDTO dto, @MappingTarget Meal meal);
}
