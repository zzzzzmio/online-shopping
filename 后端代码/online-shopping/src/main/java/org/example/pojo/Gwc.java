package org.example.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Gwc {
    @NotNull
    private Integer id;
    @NotEmpty
    private Integer goodsId;
    @NotEmpty
    private String goodsName;
    @NotNull
    private Integer price;
    @NotEmpty
    private String coverImg;
    @NotNull
    private Integer totalPrice;
    @NotNull
    private Integer shopUser;
    @NotNull
    private Integer goodsNum;
    @NotNull
    private Integer createUser;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
}
