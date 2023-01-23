package com.lcwd.electronic.store.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class CategoryDto {
    private String categoryId;

    @NotBlank(message = "title required !!")
    @Size(min = 4)
    private String title;

    @NotBlank(message = "description required !!")
    private String description;


    private String coverImage;
}
