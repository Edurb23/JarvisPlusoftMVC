package br.com.jarvis.plusoftMVC.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB_PL_MVC_TELEFONE")
@SequenceGenerator(name = "seq_telefone", sequenceName = "SEQ_TB_PL_TELEFONE", allocationSize = 1)
public class Telefone {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seq_telefone")
    @Column(name="ID_TELEFONE", length = 8)
    private Long id;

    @Column(name = "NR_TELEFONE", length = 11, nullable = false, unique = true)
    private String numeroTelefone;

    @Column(name = "DDD_TELEFONE", length = 2, nullable = false)
    private String ddd;




}
