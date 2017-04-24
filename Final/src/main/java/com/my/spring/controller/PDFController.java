package com.my.spring.controller;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.my.spring.pojo.Address;
import com.my.spring.pojo.Cart;
import com.my.spring.pojo.Order;


public class PDFController extends AbstractPdfView{

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter pdfwriter, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        // TODO Auto-generated method stub
        Font  helvetica_18_blue = new Font(Font.HELVETICA, 18, Font.BOLDITALIC, Color.BLUE);
        Paragraph title = new Paragraph("Order Details", helvetica_18_blue);
        
        Phrase firstPhrase = new Phrase("Details of the Order");
       
       @SuppressWarnings("unchecked")
        List<Order> list = (List<Order>)model.get("list");
        Address add = (Address)model.get("address");
        String salesOrderID = (String)model.get("salesOrderID");
       
       
       
//        System.out.println("Order Id "+orderId);
       
        Paragraph header = new Paragraph("Sales Order Recipt",FontFactory.getFont(FontFactory.TIMES, 18,Font.UNDERLINE));
        document.add(header);
        
       document.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------"));
       document.add(new Paragraph(""));
       document.add(new Paragraph(""));
       document.add(new Paragraph("Sales order Number: "+salesOrderID ,FontFactory.getFont(FontFactory.HELVETICA, 15,Font.TIMES_ROMAN)));
       document.add(new Paragraph(""));
       document.add(Chunk.NEWLINE);
       
       PdfPTable table = new PdfPTable(4);
       table.setTotalWidth(500);
       table.setLockedWidth(true);
       table.setWidths(new float[]{1, 1, 1,1});
       PdfPCell cell;
       
       table.addCell(new Paragraph("Product Name",FontFactory.getFont(FontFactory.TIMES, 15,Font.BOLD)));
       table.addCell(new Paragraph("Quantity",FontFactory.getFont(FontFactory.TIMES, 15,Font.BOLD)));
       table.addCell(new Paragraph("Unit Price",FontFactory.getFont(FontFactory.TIMES, 15,Font.BOLD)));
       table.addCell(new Paragraph("Total Price",FontFactory.getFont(FontFactory.TIMES, 15,Font.BOLD)));
      
       
       long totalprice =0;
       for(Order order:list)
       {
           table.addCell(String.valueOf(order.getProduct().getTitle()));
           table.addCell(String.valueOf(order.getQuantity()));
           table.addCell(String.valueOf("$"+order.getProduct().getPrice()));
           table.addCell(String.valueOf("$"+(order.getQuantity())*(order.getProduct().getPrice())));
           totalprice = totalprice +((order.getQuantity())*(order.getProduct().getPrice()));
       }
       
       document.add(table);
       
       Paragraph totalPrice = new Paragraph("Total Cost : $"+totalprice,FontFactory.getFont(FontFactory.TIMES, 14,Font.UNDERLINE));
       document.add(totalPrice);
       
       document.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------"));
       document.add(new Paragraph(""));
       document.add(new Paragraph(""));
       document.add(new Paragraph(""));
       document.add(Chunk.NEWLINE);
       
       Paragraph address = new Paragraph("Your order will be shipped to : ",FontFactory.getFont(FontFactory.TIMES, 13,Font.UNDERLINE));
       Paragraph address1 = new Paragraph(add.getStreetAddress()+",",FontFactory.getFont(FontFactory.TIMES, 13));
       Paragraph address2 = new Paragraph(add.getCity()+","+add.getState()+",",FontFactory.getFont(FontFactory.TIMES, 13));
       Paragraph address3 = new Paragraph(add.getCountry()+","+add.getZipCode()+".",FontFactory.getFont(FontFactory.TIMES, 13));
       
       document.add(address);
       document.add(address1);
       document.add(address2);
       document.add(address3);
    }
    
    

}
