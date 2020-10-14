package infra;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileSaver {

	@Autowired
	private HttpServletRequest request;
	
	public String write(String baseFolder, MultipartFile file) {
		try {
			String realPath = request.getServletContext().getRealPath("/" + baseFolder);//Pega o caminho "real" (pasta raiz do servidor)
			String pathname = realPath + "/" + file.getOriginalFilename();//Concatena o caminho real ao nome do arquivo
			file.transferTo(new File(pathname));//transfere o arquivo para o caminho indicado
			return baseFolder + "/" + file.getOriginalFilename(); //Retorna o caminho relativo do arquivo
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
	}
}
