package com.cathy.cathyblog.common;

import lombok.Data;

@Data
public class ApiBaseModel<E> {
    private int returncode;
    private String message;
    private E result;
}
