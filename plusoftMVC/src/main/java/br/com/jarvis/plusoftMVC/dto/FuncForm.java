package br.com.jarvis.plusoftMVC.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FuncForm {

    private String username;
    private String password;
    private List<String> roles;



}
