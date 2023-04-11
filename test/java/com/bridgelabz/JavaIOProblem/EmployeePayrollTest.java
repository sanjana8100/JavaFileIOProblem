package com.bridgelabz.JavaIOProblem;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.IntStream;

public class EmployeePayrollTest {
    private static String HOME = System.getProperty("user.home");
    private static String PLAY_WITH_NIO = "TempPlayGround";

    @Test
    public void GivenPathWhenCheckedThenConfirm() throws IOException {
        // check file exist
        Path homepath = Paths.get(HOME);
        Assertions.assertTrue(Files.exists(homepath));

        // delete file and check file not exist
        Path playPath = Paths.get(HOME + "/"+PLAY_WITH_NIO);
        if(Files.exists(playPath))
            FileUtils.deleteFiles(playPath.toFile());
        Assertions.assertTrue(Files.notExists(playPath));

        // Create Directory
        Files.createDirectories(playPath);
        Assertions.assertTrue(Files.exists(playPath));

        // Create file
        IntStream.range(1,10).forEach(counter ->{
            Path tempFile =Paths.get(playPath+"/temp"+ counter);
            Assertions.assertTrue(Files.notExists(tempFile));
            try{
                Files.createFile(tempFile);
            }
            catch (IOException e){
                System.out.println("Catch");
            }
            Assertions.assertTrue(Files.exists(tempFile));
        });

        //List files, Directories as well as Files with Extension
        Files.list(playPath).filter(Files::isRegularFile).forEach(System.out::println);
        Files.newDirectoryStream(playPath).forEach(System.out::println);
        Files.newDirectoryStream(playPath,path-> path.toFile().isFile()&&path.toString().startsWith("temp")).forEach(System.out::println);
    }
}
