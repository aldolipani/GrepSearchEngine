package at.ac.tuwien.ifs.grepsearchengine.bin

import java.io.File

import at.ac.tuwien.ifs.grepsearchengine.model.CollectionStats
import at.ac.tuwien.ifs.grepsearchengine.model.input.Collection
import at.ac.tuwien.ifs.grepsearchengine.model.index.{TopicVector, SCDt, CDt, DocumentVector}
import at.ac.tuwien.ifs.grepsearchengine.model.matching.BM25
import at.ac.tuwien.ifs.grepsearchengine.scorer.Scorer
import at.ac.tuwien.ifs.grepsearchengine.textpreprocessing.DocumentPreProcessor

/**
 * Created by aldo on 26/02/16.
 */
object Main extends App{

  override def main(args: Array[String]): Unit = time {
    val dir = "/Users/aldo/Downloads/terrier-core-4.1/src"
    val c = new Collection(new File(dir))

    val dpp = new DocumentPreProcessor()
    val ds:List[DocumentVector] = c.documents.map(d => dpp.preProcess(d))

    val scdt:SCDt = CDt.get(ds).getSCDt()

    val cs:CollectionStats = new CollectionStats(ds, scdt)

    val sm = new BM25(scdt, cs)

    val s = new Scorer(sm, ds)

    //ds.foldRight(dt)(e => e._1.add(e._2))
    val tv = new TopicVector(ds.head.id, ds.head.terms)

    println(cs.sD)
    println(s.score(tv))

    //println(ds.mkString("\n"))
  }

  def time[R](block: => R): R = {
    val t0 = System.nanoTime()
    val result = block    // call-by-name
    val t1 = System.nanoTime()
    println("Elapsed time: " + (t1 - t0)/1000000000 + "s")
    result
  }

  def printlnWithTime[R](message:String, block: => R): R = {
    val t0 = System.nanoTime()
    val result = block    // call-by-name
    val t1 = System.nanoTime()
    print(message)
    println(" - elapsed time: " + (t1 - t0)/1000000 + "ms")
    result
  }

}
