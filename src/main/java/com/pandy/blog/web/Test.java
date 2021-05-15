package com.pandy.blog.web;

import com.pandy.blog.common.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Pandy
 */
@RestController
@Api(tags = "测试模块")
public class Test {

    @GetMapping("/test")
    @ApiOperation("测试接口")
    public Result test() {
        int i = 1/0;
        return new Result().code("200").message("好了").data("data", "haha");
    }

}
