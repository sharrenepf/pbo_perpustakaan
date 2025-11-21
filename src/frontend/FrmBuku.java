package frontend;

import backend.*;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FrmBuku extends JFrame {

    private JTextField txtIdBuku, txtJudul, txtPenerbit, txtPenulis, txtCari;
    private JComboBox<kategori> cmbkategori;
    private JButton btnSimpan, btnHapus, btnTambahBaru, btnCari, btnKelolakategori;
    private JTable tblBuku;
    private JScrollPane jScrollPane1;

    public FrmBuku() {

        UIManager.put("Button.focus", new Color(0, 0, 0, 0));
        UIManager.put("Table.showGrid", false);
        UIManager.put("Table.intercellSpacing", new Dimension(8, 8));

        JLabel lblId = new JLabel("ID Buku");
        JLabel lblkategori = new JLabel("kategori");
        JLabel lblJudul = new JLabel("Judul");
        JLabel lblPenerbit = new JLabel("Penerbit");
        JLabel lblPenulis = new JLabel("Penulis");

        txtIdBuku = new JTextField();
        cmbkategori = new JComboBox<>();
        txtJudul = new JTextField();
        txtPenerbit = new JTextField();
        txtPenulis = new JTextField();
        txtCari = new JTextField();

        btnSimpan = makeButton("Simpan");
        btnHapus = makeButton("Hapus");
        btnTambahBaru = makeButton("Tambah Baru");
        btnCari = makeButton("Cari");
        btnKelolakategori = makeButton("Kelola kategori");

        txtIdBuku.setText("0");
        txtIdBuku.setEnabled(false);

        tblBuku = new JTable();
        jScrollPane1 = new JScrollPane(tblBuku);

        setLayout(null);

        Font fLabel = new Font("Segoe UI", Font.PLAIN, 14);
        Font fText = new Font("Segoe UI", Font.PLAIN, 14);
        Font fBtn = new Font("Segoe UI", Font.BOLD, 14);

        lblId.setFont(fLabel);
        lblkategori.setFont(fLabel);
        lblJudul.setFont(fLabel);
        lblPenerbit.setFont(fLabel);
        lblPenulis.setFont(fLabel);

        txtIdBuku.setFont(fText);
        cmbkategori.setFont(fText);
        txtJudul.setFont(fText);
        txtPenerbit.setFont(fText);
        txtPenulis.setFont(fText);
        txtCari.setFont(fText);

        btnSimpan.setFont(fBtn);
        btnHapus.setFont(fBtn);
        btnTambahBaru.setFont(fBtn);
        btnCari.setFont(fBtn);
        btnKelolakategori.setFont(fBtn);

        tblBuku.setFont(fText);
        tblBuku.setRowHeight(28);

        int xLabel = 30;
        int xField = 120;
        int wField = 250;
        int hField = 28;
        int gapY = 35;

        lblId.setBounds(xLabel, 30, 80, 25);
        txtIdBuku.setBounds(xField, 30, 80, hField);

        lblkategori.setBounds(xLabel, 30 + gapY, 80, 25);
        cmbkategori.setBounds(xField, 30 + gapY, wField, hField);

        lblJudul.setBounds(xLabel, 30 + gapY * 2, 80, 25);
        txtJudul.setBounds(xField, 30 + gapY * 2, wField, hField);

        lblPenerbit.setBounds(xLabel, 30 + gapY * 3, 80, 25);
        txtPenerbit.setBounds(xField, 30 + gapY * 3, wField, hField);

        lblPenulis.setBounds(xLabel, 30 + gapY * 4, 80, 25);
        txtPenulis.setBounds(xField, 30 + gapY * 4, wField, hField);

        btnSimpan.setBounds(xField + wField + 20, 30, 120, 35);
        btnHapus.setBounds(xField + wField + 20, 30 + gapY, 120, 35);
        btnTambahBaru.setBounds(xField + wField + 20, 30 + gapY * 2, 120, 35);

        btnKelolakategori.setBounds(xField + wField + 150, 30, 120, 35);

        txtCari.setBounds(xField + wField + 20, 30 + gapY * 3, 190, 32);
        btnCari.setBounds(xField + wField + 220, 30 + gapY * 3, 50, 32);

        jScrollPane1.setBounds(30, 30 + gapY * 5 + 20, 600, 250);

        add(lblId);
        add(txtIdBuku);
        add(lblkategori);
        add(cmbkategori);
        add(lblJudul);
        add(txtJudul);
        add(lblPenerbit);
        add(txtPenerbit);
        add(lblPenulis);
        add(txtPenulis);
        add(btnSimpan);
        add(btnHapus);
        add(btnTambahBaru);
        add(btnKelolakategori);
        add(txtCari);
        add(btnCari);
        add(jScrollPane1);

        btnSimpan.addActionListener(e -> simpan());
        btnHapus.addActionListener(e -> hapus());
        btnTambahBaru.addActionListener(e -> kosongkanForm());
        btnCari.addActionListener(e -> cari(txtCari.getText()));
        btnKelolakategori.addActionListener(e -> kelolakategori());
        tblBuku.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                isiFormDariTabel();
            }
        });

        setTitle("Form Buku");
        setSize(680, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        tampilkanCmbkategori();
        tampilkanData();
        kosongkanForm();
    }

    private JButton makeButton(String text) {
        JButton b = new JButton(text);
        b.setFocusPainted(false);
        b.setBackground(new Color(200, 220, 240));
        b.setBorder(BorderFactory.createLineBorder(new Color(150, 150, 150)));
        return b;
    }

    private void simpan() {

        if (cmbkategori.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Pilih kategori terlebih dahulu!");
            return;
        }
        if (txtJudul.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Judul harus diisi!");
            return;
        }

        Buku buku = new Buku();
        buku.setIdbuku(Integer.parseInt(txtIdBuku.getText()));
        buku.setKategori((kategori) cmbkategori.getSelectedItem());
        buku.setJudul(txtJudul.getText());
        buku.setPenulis(txtPenulis.getText());
        buku.setPenerbit(txtPenerbit.getText());
        buku.save();

        txtIdBuku.setText(String.valueOf(buku.getIdbuku()));
        tampilkanData();
        JOptionPane.showMessageDialog(this, "Data buku berhasil disimpan!");
    }

    private void hapus() {
        int row = tblBuku.getSelectedRow();
        if (row >= 0) {
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Yakin ingin menghapus data buku ini?", "Konfirmasi Hapus",
                    JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                int id = Integer.parseInt(tblBuku.getValueAt(row, 0).toString());
                Buku buku = new Buku().getById(id);
                buku.delete();
                tampilkanData();
                kosongkanForm();
                JOptionPane.showMessageDialog(this, "Data buku berhasil dihapus!");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Pilih data yang akan dihapus terlebih dahulu.");
        }
    }

    private void isiFormDariTabel() {
        int row = tblBuku.getSelectedRow();
        if (row >= 0) {
            int id = Integer.parseInt(tblBuku.getValueAt(row, 0).toString());
            Buku buku = new Buku().getById(id);

            txtIdBuku.setText(String.valueOf(buku.getIdbuku()));

            for (int i = 0; i < cmbkategori.getItemCount(); i++) {
                kategori kat = cmbkategori.getItemAt(i);
                if (kat.getIdkategori() == buku.getKategori().getIdkategori()) {
                    cmbkategori.setSelectedIndex(i);
                    break;
                }
            }

            txtJudul.setText(buku.getJudul());
            txtPenerbit.setText(buku.getPenerbit());
            txtPenulis.setText(buku.getPenulis());
        }
    }

    private void kosongkanForm() {
        txtIdBuku.setText("0");
        if (cmbkategori.getItemCount() > 0) {
            cmbkategori.setSelectedIndex(0);
        }
        txtJudul.setText("");
        txtPenerbit.setText("");
        txtPenulis.setText("");
        txtCari.setText("");
    }

    private void tampilkanData() {
        String[] kolom = { "ID", "kategori", "Judul", "Penulis", "Penerbit" };
        DefaultTableModel model = new DefaultTableModel(new Object[][] {}, kolom) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        for (Buku b : new Buku().getAll()) {
            model.addRow(new Object[] {
                    b.getIdbuku(),
                    b.getKategori().getNama(),
                    b.getJudul(),
                    b.getPenulis(),
                    b.getPenerbit()
            });
        }

        tblBuku.setModel(model);

        tblBuku.getColumnModel().getColumn(0).setPreferredWidth(40); // ID
        tblBuku.getColumnModel().getColumn(1).setPreferredWidth(100); // kategori
        tblBuku.getColumnModel().getColumn(2).setPreferredWidth(200); // Judul
        tblBuku.getColumnModel().getColumn(3).setPreferredWidth(150); // Penulis
        tblBuku.getColumnModel().getColumn(4).setPreferredWidth(150); // Penerbit
    }

    private void cari(String keyword) {
        String[] kolom = { "ID", "kategori", "Judul", "Penulis", "Penerbit" };
        DefaultTableModel model = new DefaultTableModel(new Object[][] {}, kolom) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        for (Buku b : new Buku().search(keyword)) {
            model.addRow(new Object[] {
                    b.getIdbuku(),
                    b.getKategori().getNama(),
                    b.getJudul(),
                    b.getPenulis(),
                    b.getPenerbit()
            });
        }

        tblBuku.setModel(model);
    }

    private void tampilkanCmbkategori() {
        cmbkategori.removeAllItems();
        ArrayList<kategori> listkategori = new kategori().getAll();
        for (kategori k : listkategori) {
            cmbkategori.addItem(k);
        }

        cmbkategori.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value,
                    int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof kategori) {
                    kategori kat = (kategori) value;
                    setText(kat.getNama());
                }
                return this;
            }
        });
    }

    private void kelolakategori() {

        JOptionPane.showMessageDialog(this,
                "Fitur Kelola kategori akan dibuka.\n" +
                        "Anda bisa menambah, edit, atau hapus kategori di sini.");

    }

    public void refreshDatakategori() {
        tampilkanCmbkategori();
        tampilkanData();
    }

    public static void main(String[] args) {
        new FrmBuku().setVisible(true);
    }
}