package com.personal.trainer.demo.contract;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginDTO {

    String email;
    String password;
    String type;
}
