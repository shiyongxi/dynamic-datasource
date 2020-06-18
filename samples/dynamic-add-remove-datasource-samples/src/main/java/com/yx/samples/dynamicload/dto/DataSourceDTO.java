package com.yx.samples.dynamicload.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @auther: yx
 * @Date: 2020-06-17 11:00
 * @Description: DataSourceDTO
 */
@Data
@ApiModel("数据库操作数据模型")
public class DataSourceDTO {

    @NotBlank
    @ApiModelProperty(value = "连接池名称", example = "test")
    private String pollName;

    @NotBlank
    @ApiModelProperty(value = "JDBC driver", example = "com.mysql.cj.jdbc.Driver")
    private String driverClassName;

    @NotBlank
    @ApiModelProperty(value = "JDBC url 地址", example = "jdbc:mysql://127.0.0.1:3306/test")
    private String url;

    @NotBlank
    @ApiModelProperty(value = "JDBC 用户名", example = "root")
    private String username;

    @ApiModelProperty(value = "JDBC 密码", example = "")
    private String password;

}
