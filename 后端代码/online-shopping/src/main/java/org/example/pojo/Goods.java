package org.example.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Goods {
    @NotNull
    private Integer id;
    @NotEmpty
    private String goodsName;
    @NotEmpty
    private String content;
    @NotEmpty
    private String coverImg;
    @NotNull
    private Integer categoryId;
    @NotNull
    private Integer price;
    @NotNull
    private Integer createUser;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
}
