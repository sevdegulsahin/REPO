

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MainFrame extends JFrame {
    private CustomerManager customerManager;
    private CityTree cityTree;

    public MainFrame() {
        // Başlangıç
        customerManager = new CustomerManager();
        cityTree = new CityTree();

        // Pencere Ayarları
        setTitle("Kargo Yönetim Sistemi");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Ana Menü Paneli
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(6, 1, 10, 10));

        JButton addCustomerButton = new JButton("1. Yeni müşteri ekle");
        JButton addShipmentButton = new JButton("2. Kargo gönderimi ekle");
        JButton queryShipmentButton = new JButton("3. Kargo durumu sorgula");
        JButton viewShipmentHistoryButton = new JButton("4. Gönderim geçmişini görüntüle");
        JButton listAllShipmentsButton = new JButton("5. Tüm kargoları listele");
        JButton showRoutesButton = new JButton("6. Teslimat rotalarını göster");

        menuPanel.add(addCustomerButton);
        menuPanel.add(addShipmentButton);
        menuPanel.add(queryShipmentButton);
        menuPanel.add(viewShipmentHistoryButton);
        menuPanel.add(listAllShipmentsButton);
        menuPanel.add(showRoutesButton);

        add(menuPanel, BorderLayout.CENTER);

        // Olay Dinleyiciler
        addCustomerButton.addActionListener(e -> showAddCustomerDialog());
        addShipmentButton.addActionListener(e -> showAddShipmentDialog());
        queryShipmentButton.addActionListener(e -> showQueryShipmentDialog());
        viewShipmentHistoryButton.addActionListener(e -> showShipmentHistoryDialog());
        listAllShipmentsButton.addActionListener(e -> showAllShipmentsDialog());
        showRoutesButton.addActionListener(e -> showDeliveryRoutesDialog());
    }

    // 1. Yeni Müşteri Ekle
    private void showAddCustomerDialog() {
        JDialog dialog = new JDialog(this, "Yeni Müşteri Ekle", true);
        dialog.setLayout(new GridLayout(3, 2, 10, 10));

        JLabel idLabel = new JLabel("Müşteri ID:");
        JTextField idField = new JTextField();
        JLabel nameLabel = new JLabel("Ad ve Soyad:");
        JTextField nameField = new JTextField();

        JButton addButton = new JButton("Ekle");
        JButton cancelButton = new JButton("İptal");

        dialog.add(idLabel);
        dialog.add(idField);
        dialog.add(nameLabel);
        dialog.add(nameField);
        dialog.add(addButton);
        dialog.add(cancelButton);

        addButton.addActionListener(e -> {
            String customerID = idField.getText().trim();
            String name = nameField.getText().trim();

            if (customerID.isEmpty() || name.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "Lütfen tüm alanları doldurun.", "Hata", JOptionPane.ERROR_MESSAGE);
            } else {
                customerManager.addCustomer(customerID, name);
                JOptionPane.showMessageDialog(dialog, "Müşteri başarıyla eklendi.", "Bilgi", JOptionPane.INFORMATION_MESSAGE);
                dialog.dispose();
            }
        });

        cancelButton.addActionListener(e -> dialog.dispose());

        dialog.setSize(400, 200);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    // 2. Kargo Gönderimi Ekle
    private void showAddShipmentDialog() {
        JDialog dialog = new JDialog(this, "Kargo Gönderimi Ekle", true);
        dialog.setLayout(new GridLayout(4, 2, 10, 10));

        JLabel customerIdLabel = new JLabel("Müşteri ID:");
        JTextField customerIdField = new JTextField();
        JLabel destinationLabel = new JLabel("Varış Şehri:");
        JComboBox<String> destinationBox = new JComboBox<>();

        for (CityNode city : cityTree.getAllCities()) {
            destinationBox.addItem(city.getCityName());
        }

        JButton addButton = new JButton("Ekle");
        JButton cancelButton = new JButton("İptal");

        dialog.add(customerIdLabel);
        dialog.add(customerIdField);
        dialog.add(destinationLabel);
        dialog.add(destinationBox);
        dialog.add(addButton);
        dialog.add(cancelButton);

        addButton.addActionListener(e -> {
            String customerID = customerIdField.getText().trim();
            String destination = (String) destinationBox.getSelectedItem();

            if (customerID.isEmpty() || destination == null) {
                JOptionPane.showMessageDialog(dialog, "Lütfen tüm alanları doldurun.", "Hata", JOptionPane.ERROR_MESSAGE);
            } else {
                Customer customer = customerManager.getCustomers().get(customerID);
                if (customer == null) {
                    JOptionPane.showMessageDialog(dialog, "Müşteri bulunamadı.", "Hata", JOptionPane.ERROR_MESSAGE);
                } else {
                    int deliveryTime = cityTree.calculateDeliveryTime(destination);
                    Shipment shipment = new Shipment("SHP" + System.currentTimeMillis(), "Bugün", false, deliveryTime);
                    customer.getShipmentHistory().addShipment(shipment);
                    JOptionPane.showMessageDialog(dialog, "Kargo başarıyla eklendi.", "Bilgi", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        cancelButton.addActionListener(e -> dialog.dispose());

        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    // 3. Kargo Durumu Sorgula
    private void showQueryShipmentDialog() {
        JDialog dialog = new JDialog(this, "Kargo Durumu Sorgula", true);
        dialog.setLayout(new GridLayout(2, 2, 10, 10));

        JLabel shipmentIdLabel = new JLabel("Kargo ID:");
        JTextField shipmentIdField = new JTextField();

        JButton queryButton = new JButton("Sorgula");
        JButton cancelButton = new JButton("İptal");

        dialog.add(shipmentIdLabel);
        dialog.add(shipmentIdField);
        dialog.add(queryButton);
        dialog.add(cancelButton);

        queryButton.addActionListener(e -> {
            String shipmentID = shipmentIdField.getText().trim();

            Shipment result = searchShipmentById(shipmentID);
            if (result != null) {
                String message = String.format(
                        "Kargo ID: %s\nDurum: %s\nTeslim Süresi: %d gün",
                        result.getShipmentID(),
                        result.isDelivered() ? "Teslim Edildi" : "Teslim Edilmedi",
                        result.getDeliveryTime()
                );
                JOptionPane.showMessageDialog(dialog, message, "Kargo Durumu", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(dialog, "Kargo bulunamadı.", "Hata", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelButton.addActionListener(e -> dialog.dispose());

        dialog.setSize(400, 200);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    private Shipment searchShipmentById(String shipmentID) {
        for (Customer customer : customerManager.getCustomers().values()) {
            for (Shipment shipment : customer.getShipmentHistory().getShipments()) {
                if (shipment.getShipmentID().equals(shipmentID)) {
                    return shipment;
                }
            }
        }
        return null;
    }

    // 4. Gönderim Geçmişini Görüntüle
    private void showShipmentHistoryDialog() {
        JDialog dialog = new JDialog(this, "Gönderim Geçmişi", true);
        dialog.setLayout(new BorderLayout());

        JLabel customerIdLabel = new JLabel("Müşteri ID:");
        JTextField customerIdField = new JTextField();
        JButton queryButton = new JButton("Göster");

        JPanel inputPanel = new JPanel(new GridLayout(1, 2));
        inputPanel.add(customerIdLabel);
        inputPanel.add(customerIdField);

        JTextArea historyArea = new JTextArea();
        historyArea.setEditable(false);

        queryButton.addActionListener(e -> {
            String customerID = customerIdField.getText().trim();
            Customer customer = customerManager.getCustomers().get(customerID);

            if (customer != null) {
                StringBuilder history = new StringBuilder("Gönderim Geçmişi:\n");
                for (Shipment shipment : customer.getShipmentHistory().getShipments()) {
                    history.append(String.format(
                            "Kargo ID: %s, Tarih: %s, Durum: %s, Teslim Süresi: %d gün\n",
                            shipment.getShipmentID(),
                            shipment.getDate(),
                            shipment.isDelivered() ? "Teslim Edildi" : "Teslim Edilmedi",
                            shipment.getDeliveryTime()
                    ));
                }
                historyArea.setText(history.toString());
            } else {
                JOptionPane.showMessageDialog(dialog, "Müşteri bulunamadı.", "Hata", JOptionPane.ERROR_MESSAGE);
            }
        });

        dialog.add(inputPanel, BorderLayout.NORTH);
        dialog.add(new JScrollPane(historyArea), BorderLayout.CENTER);
        dialog.add(queryButton, BorderLayout.SOUTH);

        dialog.setSize(500, 400);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    // 5. Tüm Kargoları Listele
    private void showAllShipmentsDialog() {
        JDialog dialog = new JDialog(this, "Tüm Kargolar", true);
        dialog.setLayout(new BorderLayout());

        JTextArea shipmentsArea = new JTextArea();
        shipmentsArea.setEditable(false);

        JButton closeButton = new JButton("Kapat");
        closeButton.addActionListener(e -> dialog.dispose());

        StringBuilder shipmentsList = new StringBuilder("Tüm Kargolar:\n");
        List<Shipment> allShipments = customerManager.getAllShipments();

        for (Shipment shipment : allShipments) {
            shipmentsList.append(String.format(
                    "Kargo ID: %s, Durum: %s, Teslim Süresi: %d gün\n",
                    shipment.getShipmentID(),
                    shipment.isDelivered() ? "Teslim Edildi" : "Teslim Edilmedi",
                    shipment.getDeliveryTime()
            ));
        }
        shipmentsArea.setText(shipmentsList.toString());

        dialog.add(new JScrollPane(shipmentsArea), BorderLayout.CENTER);
        dialog.add(closeButton, BorderLayout.SOUTH);

        dialog.setSize(500, 400);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    // 6. Teslimat Rotalarını Göster
    private void showDeliveryRoutesDialog() {
        JOptionPane.showMessageDialog(this, "Teslimat rotaları henüz uygulanmadı.", "Bilgi", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
        });
    }
}
