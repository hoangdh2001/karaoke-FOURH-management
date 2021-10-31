package test;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingWorker;
import javax.swing.table.AbstractTableModel;

/**
 * @see https://stackoverflow.com/a/34742409/230513
 * @see https://stackoverflow.com/a/24762078/230513
 */
public class WorkerTest {

    private static final int N = 1_000;
    private static final String URL = "jdbc:sqlserver://localhost:1433;databasename=QLCongTyDuLich";
    private static final Random r = new Random();

    private void display() {
        JFrame f = new JFrame("WorkerTest");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        createTestDatabase(N);
        JDBCModel model = new JDBCModel(getConnection(), "select * from DiaDanh");
        f.add(new JScrollPane(new JTable(model) {

            @Override
            public Dimension getPreferredScrollableViewportSize() {
                return new Dimension(320, 240);
            }
        }));
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }

    private static class Row {
        int ID;
        String name;
    }

    private static class JDBCModel extends AbstractTableModel {

        private final List<DiaDanh> data = new ArrayList<>();
        private ResultSet rs = null;
        private ResultSetMetaData meta;

        public JDBCModel(Connection conn, String query) {
            try {
                Statement s = conn.createStatement();
                rs = s.executeQuery(query);
                meta = rs.getMetaData();
                JDBCWorker worker = new JDBCWorker();
                worker.execute();
            } catch (SQLException e) {
                e.printStackTrace(System.err);
            }
        }

        @Override
        public int getRowCount() {
            return data.size();
        }

        @Override
        public int getColumnCount() {
            try {
                return meta.getColumnCount();
            } catch (SQLException e) {
                e.printStackTrace(System.err);
            }
            return 0;
        }

        @Override
        public Object getValueAt(int rowIndex, int colIndex) {
            DiaDanh diaDanh = data.get(rowIndex);
            switch (colIndex) {
                case 0:
                    return diaDanh.getMaDiaDanh();
                case 1:
                    return diaDanh.getTenDiaDanh();
                case 2:
                    return diaDanh.getAnhDiaDanh();
                case 3:
                    return diaDanh.getThuocTinh();
            }
            return null;
        }

        @Override
        public String getColumnName(int colIndex) {
            try {
                return meta.getColumnName(colIndex + 1);
            } catch (SQLException e) {
                e.printStackTrace(System.err);
            }
            return null;
        }

        private class JDBCWorker extends SwingWorker<List<DiaDanh>, DiaDanh> {

            @Override
            protected List<DiaDanh> doInBackground() {
                try {
                    while (rs.next()) {
                        DiaDanh diaDanh = new DiaDanh();
                        diaDanh.setMaDiaDanh(rs.getString("maDiaDanh"));
                        diaDanh.setTenDiaDanh(rs.getString("tenDiaDanh"));
                        diaDanh.setThuocTinh(rs.getString("thuocTinh"));
                        diaDanh.setAnhDiaDanh(rs.getBytes("anhDiaDanh"));
                        publish(diaDanh);
                    }
                } catch (SQLException e) {
                    e.printStackTrace(System.err);
                }
                return data;
            }

            @Override
            protected void process(List<DiaDanh> chunks) {
                int n = getRowCount();
                for (DiaDanh chunk : chunks) {
                    data.add(chunk);
                }
                fireTableRowsInserted(n, n + chunks.size());
            }
        }
    }

    private static void createTestDatabase(int n) {
        Connection conn = getConnection();
        try {
            Statement st = conn.createStatement();
            st.execute("create table city(id integer, name varchar2)");
            PreparedStatement ps = conn.prepareStatement(
                "insert into city values (?, ?)");
            for (int i = 0; i < n; i++) {
                ps.setInt(1, i);
                ps.setString(2, (char) ('A' + r.nextInt(26))
                    + String.valueOf(r.nextInt(1_000_000)));
                ps.execute();
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
        }
    }

    private static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, "sa", "huyhoang14901");
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
        return null;
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new WorkerTest()::display);
    }
}