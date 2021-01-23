package com.lukaskj.irest.register.dto;

import com.lukaskj.irest.register.entity.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "cdi")
public interface RestaurantMapper {
   @Mapping(target = "name", source = "companyName")
   @Mapping(target = "id", ignore = true)
   @Mapping(target = "createdAt", ignore = true)
   @Mapping(target = "updatedAt", ignore = true)
   // @Mapping(target = "location.id", ignore = true)
   @Mapping(target = "location.createdAt", ignore = true)
   @Mapping(target = "location.updatedAt", ignore = true)
   public Restaurant toRestaurant(AddRestaurantDTO dto);

   @Mapping(target = "name", source = "companyName")
   @Mapping(target = "createdAt", ignore = true)
   @Mapping(target = "updatedAt", ignore = true)
   // @Mapping(target = "location.id", ignore = true)
   // @Mapping(target = "location.createdAt", ignore = true)
   // @Mapping(target = "location.updatedAt", ignore = true)
   public void toRestaurant(UpdateRestaurantDTO dto, @MappingTarget Restaurant restaurant);

   @Mapping(target = "companyName", source = "name")
   @Mapping(target = "createdAt", dateFormat = "dd/MM/yyyy HH:mm:ss")
   @Mapping(target = "updatedAt", dateFormat = "dd/MM/yyyy HH:mm:ss")
   // @Mapping(target = "location.id", ignore = true)
   // @Mapping(target = "location.createdAt", ignore = true)
   // @Mapping(target = "location.updatedAt", ignore = true)
   public RestaurantDTO toRestaurantDTO(Restaurant dto);
}
