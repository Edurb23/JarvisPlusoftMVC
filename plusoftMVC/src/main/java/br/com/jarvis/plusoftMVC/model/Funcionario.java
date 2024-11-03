package br.com.jarvis.plusoftMVC.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Set;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB_PL_MVC_Funcionario")
@SequenceGenerator(name = "seq_funcionario", sequenceName = "SEQ_TB_PL_Funcionario", allocationSize = 1)
public class Funcionario {

    @Id
    @Column(name = "ID_FUNCIONARIO")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_funcionario") // Geração automática de ID usando sequência
    private Long id;

    @Column(name = "NM_USERNAME", length = 150, nullable = false)
    private String username;

    @Column(name = "DS_PASSWORD", nullable = false)
    private String password;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "TB_PL_MVC_Funcionario_Roles",
            joinColumns = @JoinColumn(name="Func_id"),
            inverseJoinColumns = @JoinColumn(name="Role_id") )
    public Set<Role> roles;



    public Funcionario(String username, String password, Set<Role> listaRoles) {
        this.username = username;
        this.password = password;
        this.roles = listaRoles;

    }
}

