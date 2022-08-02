package web;


import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itextpdf.io.IOException;
import com.itextpdf.io.font.FontProgram;
import com.itextpdf.io.font.FontProgramFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;

import dao.DaoImp;
import dao.IDao;
import entities.Client;
import entities.Paiement;
import service.IService;
import service.ServiceImp;

@WebServlet("/generatepdf")
public class PDFExport extends HttpServlet{
	
	private IDao dao;
	private IService service;
	@Override
	public void init() throws ServletException {
		dao = new DaoImp();
		service = new ServiceImp();
	}
	

    @Override
    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, java.io.IOException {
    	HttpSession session = request.getSession();
    	session.invalidate();
    	Long idClient = Long.parseLong(request.getParameter("client"));
    	List<Paiement> listPaiement = service.getAllPaiements(idClient);
    	Paiement paiement = listPaiement.get(listPaiement.size()-1);
    	Client client = dao.getClient(idClient);
        
        String masterPath = request.getServletContext().getRealPath( "/WEB-INF/starter.pdf" );
        response.setContentType( "application/pdf" );
        
        try ( PdfReader reader = new PdfReader( masterPath );
              PdfWriter writer = new PdfWriter( response.getOutputStream() );
              PdfDocument document = new PdfDocument( reader, writer ) ) {
            
            PdfPage page = document.getPage( 1 );
            PdfCanvas canvas = new PdfCanvas( page );
            
            FontProgram fontProgram = FontProgramFactory.createFont();
            PdfFont font = PdfFontFactory.createFont( fontProgram, "utf-8", true );
            canvas.setFontAndSize( font, 12 );
            
            canvas.beginText();
            
            canvas.setTextMatrix( 120, 760);
            canvas.showText( "" + client.getId() );
            
            canvas.setTextMatrix( 132, 736);
            canvas.showText( "" + client.getAccountNumber() );
            
            canvas.setTextMatrix( 157, 713);
            canvas.showText( "" + client.getFirstName() );
            
            
            canvas.setTextMatrix( 99, 580);
            canvas.showText( "" + paiement.getId() );
            
            canvas.setTextMatrix( 175, 580);
            SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/YYYY hh:mm:ss");
            canvas.showText( "" + sdf.format(paiement.getDate()) );
            
            canvas.setTextMatrix( 345, 580);
            canvas.showText( "" + paiement.getMontant() );
            
            canvas.setTextMatrix( 463, 580);
            canvas.showText( "" + paiement.getAbonnementName() );
            

            
            canvas.endText();
            
        }
        
    }

}
