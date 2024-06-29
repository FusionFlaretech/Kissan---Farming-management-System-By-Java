package signup;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.axis.TickUnitSource;
import org.jfree.chart.axis.TickUnits;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.List;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TransactionSummaryForm extends JFrame {

    private DefaultTableModel tableModel;
    private JLabel totalRevenueLabel;

    public TransactionSummaryForm(List<Transaction> transactions) {
        // Set the system look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        getContentPane().setBackground(new Color(169, 169, 169));

        // Create the table model
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Transaction ID");
        tableModel.addColumn("Crop ID");
        tableModel.addColumn("Transaction Type");
        tableModel.addColumn("Quantity");
        tableModel.addColumn("Transaction Date");
        tableModel.addColumn("Description");

        // Create the table using the model
        JTable transactionTable = new JTable(tableModel);
        transactionTable.getTableHeader().setReorderingAllowed(false); // Disable column reordering

        // Set the table background color to a light grey
        transactionTable.setBackground(new Color(230, 230, 230));

        // Set a custom TableCellRenderer for the grey background
        transactionTable.setDefaultRenderer(Object.class, new CustomTableCellRenderer());
        getContentPane().setLayout(null);

        // Add the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(transactionTable);
        scrollPane.setBounds(115, 72, 580, 298);
        scrollPane.setBorder(new TitledBorder(null, "Revenue Details", TitledBorder.LEADING, TitledBorder.TOP, null, null)); // Add a border with title
        getContentPane().add(scrollPane);

        // Create a panel for the labels
        JPanel labelPanel = new JPanel();
        labelPanel.setBounds(0, 0, 859, 53);
        labelPanel.setBackground(new Color(169, 169, 169));
        labelPanel.setLayout(new BorderLayout());

        // Add a title label
        JLabel titleLabel = new JLabel("REVENUE INSIGHTS");
        titleLabel.setBackground(new Color(169, 169, 169));
        titleLabel.setFont(new Font("Lucida Calligraphy", Font.BOLD | Font.ITALIC, 24));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        labelPanel.add(titleLabel, BorderLayout.CENTER);

        getContentPane().add(labelPanel);

        // Add a summary graph panel
        JPanel graphPanel = createSummaryGraphPanel(transactions);
        getContentPane().add(graphPanel);

        // Populate the table with transaction data
        loadTransactionData(transactions);

        // Calculate and display total revenue
        double totalRevenue = calculateTotalRevenue(transactions);
        
                // Add a label for total revenue
                totalRevenueLabel = new JLabel("Total Revenue: $0.0");
                totalRevenueLabel.setBounds(343, 616, 191, 19);
                getContentPane().add(totalRevenueLabel);
                totalRevenueLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 16));
                totalRevenueLabel.setForeground(new Color(0, 0, 0));
                totalRevenueLabel.setText("Total Revenue: $" + totalRevenue);
                
                JButton btnNewButton = new JButton("BACK");
                btnNewButton.setFont(new Font("Stencil", Font.ITALIC, 11));
                btnNewButton.setBackground(new Color(128, 128, 128));
                btnNewButton.addActionListener(new ActionListener() {
                	public void actionPerformed(ActionEvent e) {
                		
                    		admin form1 = new admin();
                    	form1.setVisible(true);
                    	dispose();
                	}
                });
                btnNewButton.setBounds(779, 616, 89, 23);
                getContentPane().add(btnNewButton);

        setTitle("Revenue Insights"); // Change the title here
        setSize(894, 674);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true);
    }

    private JPanel createSummaryGraphPanel(List<Transaction> transactions) {
        CategoryDataset dataset = createDataset(transactions);
        JFreeChart chart = createChart(dataset);

        // Create a panel to hold the chart
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(500, 200));
        chartPanel.setBackground(new Color(169, 169, 169)); // Set background color for the chart panel

        // Create a panel to hold the chart panel
        JPanel graphPanel = new JPanel(new BorderLayout());
        graphPanel.setBounds(118, 381, 580, 222);
        graphPanel.setBorder(BorderFactory.createTitledBorder("Summary Graph"));
        graphPanel.add(chartPanel, BorderLayout.NORTH);
        chartPanel.setLayout(null);

        return graphPanel;
    }

    private CategoryDataset createDataset(List<Transaction> transactions) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Add data to the dataset
        for (Transaction transaction : transactions) {
            dataset.addValue(transaction.getAmount(), "Revenue", String.valueOf(transaction.getTransactionId()));
        }

        return dataset;
    }

    private JFreeChart createChart(CategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createBarChart(
                "Transaction Revenue",
                "Transaction ID",
                "Revenue ($)",
                dataset,
                PlotOrientation.VERTICAL,
                false, // Include legend
                true,
                false
        );

        // Customize the chart appearance
        chart.setBackgroundPaint(new Color(240, 240, 240));

        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setBackgroundPaint(new Color(230, 230, 230));
        plot.setRangeGridlinePaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.lightGray);

        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, new Color(0, 128, 0)); // Set bar color to green
        renderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator());
       // renderer.isSeriesVisibleInLegend(true);

        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setTickUnit(new NumberTickUnit(1000)); // Customize the tick unit

        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setTickLabelFont(new Font("Arial", Font.PLAIN, 10)); // Customize the tick label font

        return chart;
    }

    private void loadTransactionData(List<Transaction> transactions) {
        // Clear the table before updating
        tableModel.setRowCount(0);

        // Display the transaction data in the table
        for (Transaction transaction : transactions) {
            Object[] rowData = {
                    transaction.getTransactionId(),
                    transaction.getCropId(),
                    transaction.getTransactionType(),
                    transaction.getquantity(),
                    transaction.getTransactionDate(),
                    transaction.getDescription()
            };
            tableModel.addRow(rowData);
        }
    }

    private double calculateTotalRevenue(List<Transaction> transactions) {
        return transactions.stream().mapToDouble(Transaction::getAmount).sum();
    }

    // Custom TableCellRenderer for JTable cells
    private class CustomTableCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            // Set a light grey background for the JTable cells
            cellComponent.setBackground(new Color(230, 230, 230));

            return cellComponent;
        }
    }

  
    }

