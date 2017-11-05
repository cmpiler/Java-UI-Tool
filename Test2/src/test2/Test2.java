/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test2;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.List;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.TransferHandler;

public class Test2 extends JFrame{
    
 private Resizable res;
 private JWriter writer;
 private String elements = "";
 private static ArrayList<JComponent> list = new ArrayList<JComponent>();;
 
    public Test2() {

        initUI();
    }

    private void saveJson(){
         
            for(int i = 0; i < list.size(); i++){
                   Component component = list.get(i).getComponent(i);
                if(component instanceof JLabel){
                    JLabel label = (JLabel)list.get(i).getComponent(i);
                    String text = label.getText();
                    elements += "lbl:location-" + list.get(i).getLocation() + ",text-" + text + ";";
                }
                
                else if(component instanceof JButton){
                    JButton button = (JButton)list.get(i).getComponent(i);
                    String text = button.getText();
                    elements += "btn:location-" + list.get(i).getLocation() + ",text-" + text + ";";
                }
                
                else if(component instanceof JTextField){
                    JTextField txt = (JTextField)list.get(i).getComponent(i);
                    String text = txt.getText();
                    elements += "txt:location-" + list.get(i).getLocation() + ",text-" + text + ";";
                }
            }
            System.out.println(elements);
            writer.write(elements);
    }
    
    private void initUI() {
        JPanel pnl = new JPanel(null);
        add(pnl);

        JMenuBar mb = new JMenuBar();
        JMenu menu1 = new JMenu("Save");
        mb.add(menu1);
        setJMenuBar(mb);
        
        menu1.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {

                saveJson();
            }
        });
        
        JPanel area = new JPanel();
        area.setBackground(Color.white);
        area.setLayout(null);
        res = new Resizable(area);
        res.setBounds(50, 50, 1000, 600);
        
        area.setTransferHandler(new ValueImport());
        
        pnl.add(res);
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {

                requestFocus();
                res.repaint();     
            }

            @Override
            public void mouseExited(MouseEvent me){
                res.validate();
            }
        });
        
        JPanel tools = new JPanel(new GridBagLayout());
        tools.setBackground(Color.white);
        tools.setLocation(1220,0);
        tools.setSize(150,768);
        GridBagConstraints gbc =  new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.anchor = gbc.NORTH;
        
        JLabel jl1 = new JLabel ("UI Elements:");
        JLabel jl2 = new JLabel ("Label");
        JLabel jl3 = new JLabel ("Button");
        JLabel jl4 = new JLabel ("Textbox");
        
        Font font = jl1.getFont().deriveFont(Font.PLAIN, 20f);
        jl1.setFont(font);
        jl2.setFont(font);
        jl3.setFont(font);
        jl4.setFont(font);
        
        tools.add(jl1,gbc);
        tools.add(jl2,gbc);
        tools.add(jl3,gbc);
        tools.add(jl4,gbc);
        
        jl2.setTransferHandler(new ValueExport("Label"));
        jl3.setTransferHandler(new ValueExport("Button"));
        jl4.setTransferHandler(new ValueExport("Textbox"));
        
        jl2.addMouseMotionListener(new MouseAdapter(){
                @Override
                public void mouseDragged(MouseEvent e){
                    JLabel label = (JLabel) e.getSource();
                    TransferHandler handle = label.getTransferHandler();
                    handle.exportAsDrag(label, e, TransferHandler.COPY);
                    
                }
        });
            
        jl3.addMouseMotionListener(new MouseAdapter(){
                @Override
                public void mouseDragged(MouseEvent e){
                    JLabel label = (JLabel) e.getSource();
                    TransferHandler handle = label.getTransferHandler();
                    handle.exportAsDrag(label, e, TransferHandler.COPY);
                }
        });
            
        jl4.addMouseMotionListener(new MouseAdapter(){
                @Override
                public void mouseDragged(MouseEvent e){
                    JLabel label = (JLabel) e.getSource();
                    TransferHandler handle = label.getTransferHandler();
                    handle.exportAsDrag(label, e, TransferHandler.COPY);
                }
        });
            
        pnl.add(tools);
        
        
        setSize(1366, 768);
        setTitle("Java UI Tool");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

       public static class ValueExport extends TransferHandler {

        public static final DataFlavor SUPPORTED_DATE_FLAVOR = DataFlavor.stringFlavor;
        private String value;

        public ValueExport(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        @Override
        public int getSourceActions(JComponent c) {
            return DnDConstants.ACTION_COPY_OR_MOVE;
        }

        @Override
        protected Transferable createTransferable(JComponent c) {
            Transferable t = new StringSelection(getValue());
            return t;
        }

        @Override
        protected void exportDone(JComponent source, Transferable data, int action) {
            super.exportDone(source, data, action);
            // Decide what to do after the drop has been accepted
        }

    }

     public static class ValueImport extends TransferHandler {

        public static final DataFlavor SUPPORTED_DATE_FLAVOR = DataFlavor.stringFlavor;

        public ValueImport() {
        }

        @Override
        public boolean canImport(TransferHandler.TransferSupport support) {
            return support.isDataFlavorSupported(SUPPORTED_DATE_FLAVOR);
        }

        @Override
        public boolean importData(TransferHandler.TransferSupport support) {
            boolean accept = false;
            Resizable res;
            if (canImport(support)) {
                try {
                    Transferable t = support.getTransferable();
                    Object value = t.getTransferData(SUPPORTED_DATE_FLAVOR);
                    if (value instanceof String) {
                        Component component = support.getComponent();
                        if (component instanceof JPanel) {
                            
                        switch(value.toString()){
                            case "Label":
                                
                                JLabel jl = new JLabel("Label");  
                                res = new Resizable(jl);
                                res.setBounds(450,200,100,40);
                                list.add(res);
                                /*
                                jl.addMouseMotionListener(new MouseAdapter(){
                                @Override
                                public void mouseDragged(MouseEvent E)
                                {
                                    int X=E.getX()+jl.getX();
                                    int Y=E.getY()+jl.getY();
                                    jl.setBounds(X,Y,jl.getWidth(),jl.getHeight());  
                                }                           
                                });
                                */
                                ((JPanel) component).add(res);
                                
                                break;
                                
                            case "Button":
                                JButton btn = new JButton("Button"); 
                                res = new Resizable(btn);
                                res.setBounds(450,200,100,40);
                                list.add(res);
                                /*
                                btn.addMouseMotionListener(new MouseAdapter(){
                                    
                                public void mouseDragged(MouseEvent E)
                                {
                                    int X=E.getX()+btn.getX();
                                    int Y=E.getY()+btn.getY();
                                     btn.setBounds(X,Y,btn.getWidth(),btn.getHeight());
                                }
                                });
                                */
                                ((JPanel) component).add(res);
                                break;
                            
                             case "Textbox":
                                JTextField tf = new JTextField("Textbox"); 
                                res = new Resizable(tf);
                                res.setBounds(450,200,100,40);
                                
                                Font font = tf.getFont().deriveFont(Font.PLAIN, 30f);
                                tf.setFont(font);
                                list.add(res);
                                /*
                                tf.addMouseMotionListener(new MouseAdapter(){

                                public void mouseDragged(MouseEvent E)
                                {
                                    int X=E.getX()+tf.getX();
                                    int Y=E.getY()+tf.getY();
                                    tf.setBounds(X,Y,tf.getWidth(),tf.getHeight());
                                }
                                });
                                */
                                ((JPanel) component).add(res);
                                break;   
                        }
                        //Component component = support.getComponent();
                       // if (component instanceof JPanel) {
                            //((JLabel) component).setText(value.toString());
                            accept = true;
                        }
                    }
                } catch (Exception exp) {
                    exp.printStackTrace();
                }
            }
            return accept;
        }
    }
    
    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                Test2 ex = new Test2();
                ex.setVisible(true);
            }
        });
    }
}