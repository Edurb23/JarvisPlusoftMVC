package br.com.jarvis.plusoftMVC.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "TB_PL_MVC_PRODUTO")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(name = "seq_produto", sequenceName = "seq_tb_pl_produto", allocationSize = 1)
public class Produto {

    @Id
    @Column(name = "ID_PRODUTO")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_produto")
    private Long id;

    @Column(name =  "NM_PRODUTO", length = 150, nullable = false)
    @NotBlank(message = "Nome do produto é obrigatório")
    @Size(max = 150, message = "o nome do produto deve ter no máximo 150 caracteres")
    private String nomeProduto;

    @NotBlank(message = "Categoria do produto é obrigatório")
    @Column(name = "DS_CATEGORIA", length = 150, nullable = false)
    @Enumerated(EnumType.STRING)
    private CategoriaProduto categoriaProduto;

    @NotBlank(message = "Tamanho  é obrigatório")
    @Column(name = "NR_TAMANHO", length = 25, nullable = false)
    @Enumerated(EnumType.STRING)
    private TamanhoProduto tamanhoProduto;
    @NotBlank(message = "Preco é obrigatório")
    @Column(name = "NR_PRECO", precision = 10)
    private Double preco;

    @NotBlank(message = "Descrição do produto é obrigatório")
    @Column(name = "DS_PRODUTO", length = 150)
    private  String descricao;
}
