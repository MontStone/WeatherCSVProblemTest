import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
/**
 * Write a description of Weather here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Weather {
    public CSVRecord hottestHourInFile(CSVParser parser){
        CSVRecord largestSoFar = null;
        for (CSVRecord currentRow : parser){
            if (largestSoFar == null){
                largestSoFar = currentRow;
            }
            else{
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double largestTemp = Double.parseDouble(largestSoFar.get("TemperatureF"));
                if (currentTemp > largestTemp){
                    largestSoFar = currentRow;
                }
            }
        }
        return largestSoFar;
    }
        public void testHottestHourInFile(){
        FileResource fr = new FileResource("data/2015/weather-2015-01-02.csv");
        CSVParser parser = fr.getCSVParser();
        CSVRecord largest = hottestHourInFile(parser);
        System.out.println("hottest temperature was " + largest.get("TemperatureF") + " at " + largest.get("TimeEST"));
        
    }
    
    public CSVRecord hottestInManyDays(){
       CSVRecord largestSoFar = null;
       DirectoryResource dr = new DirectoryResource();
       for (File f : dr.selectedFiles()){ //selectedfiles were gaven to f one by one
           FileResource fr = new FileResource(f); //creat a new fr and give file f to him
           CSVParser parser = fr.getCSVParser(); //get the parser from fr
           CSVRecord currentRow = hottestHourInFile(parser); //use last method to find the hottest row in this file
           if (largestSoFar == null){
               largestSoFar = currentRow;
           }
           else{
               double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
               double largestTemp = Double.parseDouble(largestSoFar.get("TemperatureF"));
               if (currentTemp > largestTemp){
                   largestSoFar = currentRow;
               }
           }
       }
       return largestSoFar;
    }
    public void testHottestInmanyDays(){
        CSVRecord largest = hottestInManyDays();
        System.out.println("hottest temperature was " + largest.get("TemperatureF") + " at " + largest.get("DateUTC"));
    }
    
    public CSVRecord lowestHumidityInFile(CSVParser parser){
        CSVRecord lowestSoFar = null;
        for (CSVRecord currentRow : parser){
            if (lowestSoFar == null){
                lowestSoFar = currentRow;
            }
            else{
                double currentH = Double.parseDouble(currentRow.get("Humidity"));
                double lowestTemp = Double.parseDouble(lowestSoFar.get("Humidity"));
                if (currentH < lowestTemp){
                    lowestSoFar = currentRow;
                }
            }
        }
        return lowestSoFar;
    }
        public void testlowestHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord lowest = lowestHumidityInFile(parser);
        System.out.println("lowest Humidity was " + lowest.get("Humidity") + " at " + lowest.get("DateUTC"));
        
    }
    
    public CSVRecord lowestHumidityInManyDays(){
       CSVRecord lowestSoFar = null;
       DirectoryResource dr = new DirectoryResource();
       for (File f : dr.selectedFiles()){ //selectedfiles were gaven to f one by one
           FileResource fr = new FileResource(f); //creat a new fr and give file f to him
           CSVParser parser = fr.getCSVParser(); //get the parser from fr
           CSVRecord currentRow = lowestHumidityInFile(parser); //use last method to find the hottest row in this file
           if (lowestSoFar == null){
               lowestSoFar = currentRow;
           }
           else{
               double currentH = Double.parseDouble(currentRow.get("Humidity"));
               double lowestTemp = Double.parseDouble(lowestSoFar.get("Humidity"));
               if (currentH < lowestTemp){
                   lowestSoFar = currentRow;
               }
           }
       }
       return lowestSoFar;
    }
    public void testlowestHumidityInManyDays(){
        CSVRecord lowest = lowestHumidityInManyDays();
        System.out.println("lowest Humidity was " + lowest.get("Humidity") + " at " + lowest.get("DateUTC"));
    }
}
