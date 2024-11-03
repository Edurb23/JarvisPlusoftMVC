package br.com.jarvis.plusoftMVC.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB_PL_MVC_CLIENTE")
@SequenceGenerator(name = "seq_cliente", sequenceName = "SEQ_TB_PL_CLIENTE", allocationSize = 1)
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seq_cliente")
    @Column(name = "ID_CLIENTE", length = 8)
    private Long id;
    @NotBlank(message = "Nome é obrigatório")
    @Size(max = 100, message = "o nome deve ter no máximo 100 caracteres")
    @Column(name = "NM_CLIENTE", length = 100,nullable = false)

    private String nome;
    @NotBlank(message = "Sobrenome é obrigatório")
    @Size(max = 100, message = "o Sobrenome deve ter no máximo 100 caracteres")
    @Column(name = "NM_SOBRENOME", length = 100,nullable = false)
    private String sobrenome;

    @NotBlank(message = "Email é obrigatório")
    @Size(max = 100, message = "o email deve ter no máximo 100 caracteres")
    @Column(name = "DS_EMAIL", length = 100,nullable = false, unique = true)
    private String email;

    @NotBlank(message = "CPF é obrigatório")
    @Size(max = 11, min = 11, message = "CPF deve ter no máximo 11 números")
    @Column(name = "NR_CPF", length = 11, nullable = false, unique = true)
    private String cpf;

    @Past
    @Column(name = "DT_NASCIMENTO",nullable = false )
    private LocalDate dataNascimento;

    @NotBlank(message = "RG é obrigatório")
    @Size(max = 9, min = 9, message = "RG deve ter no máximo 9 números")
    @Column(name = "NR_RG", length = 9, nullable = false ,unique = true)
    private  String rg;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "TELEFONE_ID")
    private Telefone telefone;



}
