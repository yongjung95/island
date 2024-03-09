package com.tictoccroc.island.response.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonResult {

    private int status;

    private String code;

    private String message;
}
