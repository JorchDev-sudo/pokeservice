package com.jorchdev.poketeams.pokeservice.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Pokemon {
    @Id
    private int id;

    private String name;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> types;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> moves;
}
