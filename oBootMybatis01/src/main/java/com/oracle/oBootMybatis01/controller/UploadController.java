package com.oracle.oBootMybatis01.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class UploadController {

   @RequestMapping(value = "upLoadFormStart")
   public String upLoadFormStart(Model model) {
      
      System.out.println("upLoadFormStart start!");
      return "upLoadFormStart";
      
   }
   
   @RequestMapping(value = "uploadForm")
   public void uploadForm() {
      
      System.out.println("uploadForm start!");
      System.out.println();
      
   }
   
   @RequestMapping(value = "uploadForm", method = RequestMethod.POST)
   public String uploadForm(HttpServletRequest request, Model model) throws IOException, ServletException {
      
      Part image = request.getPart("file1");
      InputStream inputStream = image.getInputStream();
      
      // 파일 확장자 구하기
      String fileName = image.getSubmittedFileName();
      String[] split = fileName.split("\\.");
      String originalName = split[split.length - 2];
      String suffix = split[split.length - 1];
      
      System.out.println("originalName : " + originalName);
      System.out.println("suffix : " + suffix);
      
      // Servlet 상속 받지 못했을 때 realPath 불러오는 방법
      String uploadPath = request.getSession().getServletContext().getRealPath("/upload/");
      
      System.out.println("uploadForm POST Start!");
      
      String savedName = uploadFile(originalName, inputStream, uploadPath, suffix);
      log.info("Return savedName : " + savedName);

      model.addAttribute("savedName", savedName);
      
      return "uploadResult";
      
   }

   private String uploadFile(String originalName, InputStream inputStream, String uploadPath, String suffix) throws IOException {

      // UUID : Universally Unique IDentifier
      UUID uid = UUID.randomUUID();
      
      System.out.println("uploadPath : " + uploadPath);
      
      // Directory 생성
      File fileDirectory = new File(uploadPath);
      
      if (! fileDirectory.exists()) {
         
         // 신규 폴더(Directory) 생성
         fileDirectory.mkdirs();
         System.out.println("업로드용 폴더 생성 : " + uploadPath);
         
      }
      
      String savedName = uid.toString() + "_" + originalName + "." + suffix;
      
// service 호출하고 DB CRUD 여기에 작성하면 DB에도 이미지 들어감!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
      
      log.info("savedName : " + savedName);
      
      // 임시 파일 생성
      File tempFile = new File(uploadPath + savedName);
      
      // backup 파일
      File tempFile3 = new File("c:/backup/" + savedName);
      
      FileOutputStream outputStream3 = new FileOutputStream(tempFile3);
      
      // 생성된 임시파일에, 요청으로 넘어온 file의 inputStream 복사
      // try () 안에 쓰면 close 안 해줘도 자동으로 닫힘
      try (FileOutputStream outputStream = new FileOutputStream(tempFile)) {
         
         int read;
         byte[] bytes = new byte[1024];
         
         while ((read = inputStream.read(bytes)) != -1) {
            
            // 타겟 파일에, 요청으로 넘어온 파일의 inputStream 복사
            outputStream.write(bytes, 0, read);
            
            // backup 파일에, 요청으로 넘어온 파일의 inputStream 복사
            outputStream3.write(bytes, 0, read);
            
         }
         
      } 
         
      inputStream.close();
      outputStream3.close();
         
      return savedName;
      
   }
   
   @RequestMapping(value = "uploadFileDelete", method = RequestMethod.GET)
   public String uploadFileDelete(HttpServletRequest request, Model model) {
      
      String uploadPath = request.getSession().getServletContext().getRealPath("/upload/");
      
      String deleteFile = "c:/backup/" + "4fb69e10-db31-49d3-a237-5e31f241d907_Image20240215142233.jpg";
      
      log.info("deleteFile : " + deleteFile);
      System.out.println("uploadFileDelete GET Start!");
      
      int delResult = upFileDelete(deleteFile);
      
      // DML Service 연동
      // delResult > 0 --> update member(Img)
      log.info("deleteFile 결과 : " + delResult);
      
      model.addAttribute("deleteFile", deleteFile);
      model.addAttribute("delResult", delResult);
      
      return "uploadResult";
      
   }

   private int upFileDelete(String deleteFileName) {

      int result = 0;
      
      log.info("upFileDelete 결과 : " + deleteFileName);
      
      File file = new File(deleteFileName);
      
      if (file.exists()) {
         
         if (file.delete()) {
            
            System.out.println("파일 삭제 성공");
            result = 1;
            
         } else {
            
            System.out.println("파일 삭제 실패");
            result = 0;
            
         }
         
      } else {
         
         System.out.println("삭제할 파일이 존재하지 않습니다.");
         result = -1;
         
      }
      
      return result;
   }
   
}