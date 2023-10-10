package apap.ti.silogistik2006596705.model;

import java.util.ArrayList;
import java.util.List;

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
@Table(name = "gudang")
public class Gudang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "nama", nullable = false, columnDefinition = "varchar(255)")
    private String nama;

    @NotNull
    @Column(name = "alamat_gudang", nullable = false, columnDefinition = "varchar(255)")
    private String alamatGudang;

    @OneToMany(mappedBy = "gudangId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<GudangBarang> listGudangBarang = new ArrayList<>();
}
