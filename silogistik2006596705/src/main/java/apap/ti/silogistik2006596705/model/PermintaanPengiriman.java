package apap.ti.silogistik2006596705.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
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
@Table(name = "permintaan_pengiriman")
public class PermintaanPengiriman {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pengiriman")
    private Long idPengiriman;

    @NotNull
    @Column(name = "nomor_pengiriman")
    private String nomorPengiriman;

    @NotNull
    @Column(name = "is_cancelled")
    private Boolean isCancelled;

    @NotNull
    @Column(name = "nama_penerima")
    private String namaPenerima;

    @NotNull
    @Column(name = "alamat_penerima")
    private String alamatPenerima;

    @NotNull
    @Column(name = "tanggal_pengiriman")
    private Date tanggalPengiriman;

    @NotNull
    @Column(name = "biaya_pengiriman")
    private int biayaPengiriman;

    @NotNull
    @Column(name = "jenis_layanan")
    private int jenisLayanan;

    @NotNull
    @Column(name = "waktu_permintaan")
    private LocalDateTime waktuPermintaan;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_karyawan", referencedColumnName = "id_karyawan")
    private Karyawan karyawanId;

    @OneToMany(mappedBy = "permintaanPengirimanId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PermintaanPengirimanBarang> listPermintaanPengirimanBarang = new ArrayList<>();

    @Override
    public String toString() {
        return "PermintaanPengiriman [idPengiriman=" + idPengiriman + ", nomorPengiriman=" + nomorPengiriman
                + ", isCancelled=" + isCancelled + ", namaPenerima=" + namaPenerima + ", alamatPenerima="
                + alamatPenerima + ", tanggalPengiriman=" + tanggalPengiriman + ", biayaPengiriman=" + biayaPengiriman
                + ", jenisLayanan=" + jenisLayanan + ", waktuPermintaan=" + waktuPermintaan + ", karyawanId="
                + karyawanId + ", listPermintaanPengirimanBarang=" + listPermintaanPengirimanBarang + "]";
    }
}
