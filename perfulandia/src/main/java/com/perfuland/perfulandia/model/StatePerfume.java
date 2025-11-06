package com.perfuland.perfulandia.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
@Table(name = "STATE_PERFUME")
@Data
@Entity
public class StatePerfume {
@Id
private Integer id_state;

    private String state_desc;    
}
