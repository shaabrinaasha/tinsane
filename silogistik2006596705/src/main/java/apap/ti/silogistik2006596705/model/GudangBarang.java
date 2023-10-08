package apap.ti.silogistik2006596705.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "gudang_barang")
public class GudangBarang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // https://www.baeldung.com/jpa-many-to-many
    // Many to Many with New Entity

    // referencing id column dari Gudang (PK -> FK)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_gudang", referencedColumnName = "id")
    private Gudang gudangId;

    // referencing sku column dari Barang (PK -> FK)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sku_barang", referencedColumnName = "sku")
    private Barang barangSku;

    @NotNull
    @Column(name = "stok", nullable = false)
    private int stok;
}
