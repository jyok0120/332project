package utils
import org.apache.logging.log4j.scala.Logging
import scala.util.Random
import java.io._

object SamplingUtils extends Logging {
    def sampleKeys(records: Array[Array[Byte]], sampleRatio: Float, keyLength: Int): Array[Array[Byte]] = {
        val random = new Random(System.currentTimeMillis)
        
        val sampledKeys: Array[Array[Byte]] = 
            records filter (_ => random.nextFloat <= sampleRatio) map (li => li.slice(0, keyLength))


        val outputKeys = sampledKeys.size match {
            case 0 => Array[Array[Byte]](records(0).slice(0, keyLength))
            case _ => sampledKeys
        }  
        outputKeys
    }
}