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
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.TransferHandler;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
public class Test2 extends JFrame{
    
 private Resizable res;
 private String elements = "";
 private static ArrayList<JComponent> list = new ArrayList<JComponent>();;
 
    public Test2() {

        initUI();
    }

    private void saveJson(){
         
            for(int i = 0; i < list.size(); i++){
                
                   Component component = list.get(i).getComponent(0);
                   
                   if(component instanceof JLabel){
                       
                    JLabel label = (JLabel)list.get(i).getComponent(0);
                    String tempLocation = list.get(i).getLocation().toString();
                    String locationx = "";
                    String locationy = "";
                    String[] parts;
                    
                    String text = label.getText();
                    
                    
                    locationx = tempLocation.substring(tempLocation.lastIndexOf("[") + 1);
                    parts= locationx.split(",");
                    StringBuilder sb = new StringBuilder(parts[0]);
                    sb.delete(0, 2);
                    locationx = sb.toString();
                    sb = new StringBuilder(parts[1]);
                    sb.delete(0, 2);
                    sb.deleteCharAt(sb.length()-1);
                    locationy = sb.toString();
                    
                    
                    elements += "lbl:locationx-" + locationx + ",locationy-" + locationy + ",width-" + label.getWidth() + ",height-" + label.getHeight() + ",layer-" + i + ",text-" + text + ",type-lbl" +";";
                }
                
                else if(component instanceof JButton){
                    JButton button = (JButton)list.get(i).getComponent(0);
                    String tempLocation = list.get(i).getLocation().toString();
                    String locationx = "";
                    String locationy = "";
                    String[] parts;
                    
                    String text = button.getText();
                    
                    locationx = tempLocation.substring(tempLocation.lastIndexOf("[") + 1);
                    parts= locationx.split(",");
                    StringBuilder sb = new StringBuilder(parts[0]);
                    sb.delete(0, 2);
                    locationx = sb.toString();
                    sb = new StringBuilder(parts[1]);
                    sb.delete(0, 2);
                    sb.deleteCharAt(sb.length()-1);
                    locationy = sb.toString();
             
                    elements += "btn:locationx-" + locationx + ",locationy-" + locationy + ",width-" + button.getWidth() + ",height-"+ button.getHeight() + ",layer-" + i + ",text-" + text + ",type-btn"  + ";";
                }
                
                else if(component instanceof JTextField){
                    JTextField txt = (JTextField)list.get(i).getComponent(0);
                    String tempLocation = list.get(i).getLocation().toString();
                    String locationx = "";
                    String locationy = "";
                    String[] parts;
                    
                    String text = txt.getText();
                    
                    locationx = tempLocation.substring(tempLocation.lastIndexOf("[") + 1);
                    parts= locationx.split(",");
                    StringBuilder sb = new StringBuilder(parts[0]);
                    sb.delete(0, 2);
                    locationx = sb.toString();
                    sb = new StringBuilder(parts[1]);
                    sb.delete(0, 2);
                    sb.deleteCharAt(sb.length()-1);
                    locationy = sb.toString();
                    
                    elements += "txt:locationx-" + locationx + ",locationy-" + locationy + ",width-" + txt.getWidth() +",height-"+ txt.getHeight() + ",layer-" + i + ",text-" + text + ",type-txt" +";";
                }
            }
            System.out.println(elements);
            JWriter writer = new JWriter();
            writer.write(elements);
            
    }
    
    private void loadJson(JPanel area) throws IOException, ParseException{
        Resizable resi ;
        JLabel temp = new JLabel();
        resi = new Resizable(temp);
        JSONParser parser = new JSONParser();
        JSONObject o = (JSONObject) parser.parse(new FileReader("C:\\js.json"));
        JSONArray element;
        ArrayList<Resizable> arrayList = new ArrayList<Resizable>();;
        int i = 0;
        if(o.containsKey("lbl")){
        element = (JSONArray) o.get("lbl");
            for (Object obj : element){
                i++;
            }
        }
        if(o.containsKey("btn")){
        element = (JSONArray) o.get("btn");
            for (Object obj : element){
                i++;
            }
        }
        if(o.containsKey("txt")){
        element = (JSONArray) o.get("txt");
            for (Object obj : element){
                i++;
            }    
        }
        for(int j =0; j < i; j++){
            arrayList.add(resi);
        }
        
        if(o.containsKey("lbl")){
            element = (JSONArray) o.get("lbl");
        
            for (Object obj : element){
                JSONObject jSon = (JSONObject) obj;
                
                String locationx = (String) jSon.get("locationx");
                System.out.println(locationx);

                String locationy = (String) jSon.get("locationy");
                System.out.println(locationy);

                String width = (String) jSon.get("width");
                System.out.println(width);

                String text = (String) jSon.get("text");
                System.out.println(text);
            
                String type = (String) jSon.get("type");
                System.out.println(type);
            
                String layer = (String) jSon.get("layer");
                System.out.println(layer);
            
                String height = (String) jSon.get("height");
                System.out.println(text);   
                
                JLabel jl = new JLabel(text);
                jl.setBounds(Integer.parseInt(locationx), Integer.parseInt(locationy), Integer.parseInt(width), Integer.parseInt(height));
                resi = new Resizable(jl);
                resi.setBounds(Integer.parseInt(locationx), Integer.parseInt(locationy), Integer.parseInt(width), Integer.parseInt(height));
                list.add(resi);
                
                arrayList.add(Integer.parseInt(layer), resi);
                
            }
        }
        if(o.containsKey("btn")){
            element = (JSONArray) o.get("btn");
        
            for (Object obj : element){
                JSONObject jSon = (JSONObject) obj;
                
                String locationx = (String) jSon.get("locationx");
                System.out.println(locationx);

                String locationy = (String) jSon.get("locationy");
                System.out.println(locationy);

                String width = (String) jSon.get("width");
                System.out.println(width);

                String text = (String) jSon.get("text");
                System.out.println(text);
            
                String type = (String) jSon.get("type");
                System.out.println(type);
            
                String layer = (String) jSon.get("layer");
                System.out.println(layer);
            
                String height = (String) jSon.get("height");
                System.out.println(text);   
                
                JButton btn = new JButton(text);
                btn.setBounds(Integer.parseInt(locationx), Integer.parseInt(locationy), Integer.parseInt(width), Integer.parseInt(height));
                resi = new Resizable(btn);
                resi.setBounds(Integer.parseInt(locationx), Integer.parseInt(locationy), Integer.parseInt(width), Integer.parseInt(height));
                list.add(resi);
                arrayList.add(Integer.parseInt(layer), resi);
            }
        }
        
        if(o.containsKey("txt")){
            element = (JSONArray) o.get("txt");
        
            for (Object obj : element){
                JSONObject jSon = (JSONObject) obj;
                
                String locationx = (String) jSon.get("locationx");
                System.out.println(locationx);

                String locationy = (String) jSon.get("locationy");
                System.out.println(locationy);

                String width = (String) jSon.get("width");
                System.out.println(width);

                String text = (String) jSon.get("text");
                System.out.println(text);
            
                String type = (String) jSon.get("type");
                System.out.println(type);
            
                String layer = (String) jSon.get("layer");
                System.out.println(layer);
            
                String height = (String) jSon.get("height");
                System.out.println(text);   
                
                JTextField txt = new JTextField(text);
                txt.setBounds(Integer.parseInt(locationx), Integer.parseInt(locationy), Integer.parseInt(width), Integer.parseInt(height));
                resi = new Resizable(txt);
                resi.setBounds(Integer.parseInt(locationx), Integer.parseInt(locationy), Integer.parseInt(width), Integer.parseInt(height));
                list.add(resi);
                arrayList.add(Integer.parseInt(layer), resi);
            }
        }
        for(int j = 0; j < arrayList.size(); j++){
            area.add(arrayList.get(j));
        }
    }
    
    private void initUI() {
        JPanel pnl = new JPanel(null);
        add(pnl);

        JMenuBar mb = new JMenuBar();
        JMenu menu1 = new JMenu("Save");
        JMenu menu2 = new JMenu("Load");
        mb.add(menu1);
        mb.add(menu2);
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
        
         menu2.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {

                try {
                    loadJson(area);
                } catch (IOException ex) {
                    Logger.getLogger(Test2.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(Test2.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
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
