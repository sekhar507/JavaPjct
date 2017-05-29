package org.barcode;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.aspose.barcode.*;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import javax.imageio.ImageIO;

public class BarCode extends HttpServlet
{
   public void doGet(HttpServletRequest request,HttpServletResponse response)throws IOException, ServletException
    {
        System.out.println("bar code servlet");
        // Create BarCodeBuilder instance
        BarCodeBuilder b = new BarCodeBuilder();
        // Set up symboogy
        b.setSymbologyType(Symbology.Code128);
        // Set up code text
        b.setCodeText("12345678");
        
        b.setCodeLocation(CodeLocation.None);
        
        // Render barcode
        BufferedImage image = b.getBarCodeImage();

        response.setContentType("image/png");
        OutputStream outputStream = response.getOutputStream();
        ImageIO.write(image, "png", outputStream);
        outputStream.close();
    }

}