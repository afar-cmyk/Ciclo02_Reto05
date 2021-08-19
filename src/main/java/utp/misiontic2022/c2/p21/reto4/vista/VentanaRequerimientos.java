package utp.misiontic2022.c2.p21.reto4.vista;

import java.awt.BorderLayout;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
//import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import utp.misiontic2022.c2.p21.reto4.controlador.ControladorRequerimientos;
import utp.misiontic2022.c2.p21.reto4.modelo.vo.Requerimiento_1;
import utp.misiontic2022.c2.p21.reto4.modelo.vo.Requerimiento_2;

public class VentanaRequerimientos extends JFrame {

    private JTable tabla;
    private ControladorRequerimientos controlador;

    public VentanaRequerimientos(){
        controlador = new ControladorRequerimientos();
        initUI();
        setLocationRelativeTo(null);
    }

    public void initUI(){
        setTitle("Datos del Reto #5");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800,600);
        
        var tbd = new JTabbedPane();
        getContentPane().add(tbd, BorderLayout.CENTER);

        var panel = new JPanel();
        panel.setLayout(new BorderLayout());

        tbd.addTab("Consulta de Requerimientos", panel);;

        var panelEntrada = new JPanel();

        var btnConsulta = new JButton("Requerimiento 1");
        btnConsulta.addActionListener(e -> cargarTablaConsulta());

        var btnConsulta2 = new JButton("Requerimiento 2");
        btnConsulta2.addActionListener(e -> cargarTablaConsulta2());

        var btnConsulta3 = new JButton("Requerimiento 3");
        //btnConsulta2.addActionListener(e -> );


        panelEntrada.add(btnConsulta);
        panel.add(panelEntrada, BorderLayout.PAGE_START);

        panelEntrada.add(btnConsulta2);
        panel.add(panelEntrada, BorderLayout.PAGE_START);

        panelEntrada.add(btnConsulta3);
        panel.add(panelEntrada, BorderLayout.PAGE_START);

        //Adicionar tabla
        tabla = new JTable();
        panel.add(new JScrollPane(tabla), BorderLayout.CENTER);


    }

    private void cargarTablaConsulta() {
        try {
            var lista = controlador.consultarRequerimiento1();

            var tableModel = new Requerimiento1TableModel();

            tableModel.setData(lista);

            tabla.setModel(tableModel);


            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), getTitle(), JOptionPane.ERROR_MESSAGE);
        }
    }

    private class Requerimiento1TableModel extends AbstractTableModel{
        private ArrayList<Requerimiento_1> data;

        public void setData(ArrayList<Requerimiento_1> data) {
            this.data = data;
        }

        @Override
        public String getColumnName(int column) {
            switch(column){
                case 0:
                    return "Nombre Material";
                case 1:
                    return "Cantidad";
            } 
            return super.getColumnName(column);
        }

        @Override
        public int getRowCount() {
            return data.size();
            
        }

        @Override
        public int getColumnCount() {
            return 2;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            var registro = data.get(rowIndex);
            switch(columnIndex){
                case 0:
                    return registro.getNombreMaterial();
                case 1:
                    return registro.getPrecioUnidad();
            }            

            return null;
        }

    }
    
    private void cargarTablaConsulta2() {
        try {
            var lista2 = controlador.consultarRequerimiento2();

            var tableModel2 = new Requerimiento2TableModel();

            tableModel2.setData(lista2);

            tabla.setModel(tableModel2);


            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), getTitle(), JOptionPane.ERROR_MESSAGE);
        }
    }

    private class Requerimiento2TableModel extends AbstractTableModel{

        private ArrayList<Requerimiento_2> datos;

        public void setData(ArrayList<Requerimiento_2> datos) {
            this.datos = datos;
        }
        
        @Override
        public String getColumnName(int column) {
            switch (column) {
                case 0:
                return "Nombre de la Constructora";
                case 1:
                return "Nombre de la Ciduad";
            }
            return super.getColumnName(column);
        }

        @Override
        public int getRowCount() {
            return datos.size();
        }

        @Override
        public int getColumnCount() {
            return 2;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            var registro2 = datos.get(rowIndex);

            switch (columnIndex) {
                case 0:
                return registro2.getConstructora();
                case 1:
                return registro2.getCiudad();
            }
            return null;
        }



    }
}
