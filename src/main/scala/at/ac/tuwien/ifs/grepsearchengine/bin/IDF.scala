package at.ac.tuwien.ifs.grepsearchengine.bin

import java.io.File

import at.ac.tuwien.ifs.grepsearchengine.model._
import at.ac.tuwien.ifs.grepsearchengine.model.index.{TopicVector, CDt, SCDt, DocumentVector}
import at.ac.tuwien.ifs.grepsearchengine.model.input.Collection
import at.ac.tuwien.ifs.grepsearchengine.model.matching.BM25
import at.ac.tuwien.ifs.grepsearchengine.scorer.Scorer
import at.ac.tuwien.ifs.grepsearchengine.textpreprocessing.DocumentPreProcessor
import at.ac.tuwien.ifs.grepsearchengine.textpreprocessing.tokenizer.WhiteSpaceTokeniser
import at.ac.tuwien.ifs.utility.Out

/**
 * Created by aldo on 08/03/16.
 */
object IDF extends App {

  override def main(args: Array[String]): Unit = time {
    val dir = args(0)
    //val dir = "/Users/aldo/Downloads/scollection/19980601_APW_ENG"

    val c = new Collection(new File(dir))
    val dpp = new DocumentPreProcessor(List(new WhiteSpaceTokeniser))
    val ds: TermsColumn = c.documents.foldRight(new MutableTermsColumn())((d, acc) => {printlnWithTime(d.id, addVector(dpp.preProcess(d), acc))})

    //val scdt:SCDt = CDt.get(ds).getSCDt()

    //val cs:CollectionStats = new CollectionStats(ds, scdt)

    //val sm = new BM25(scdt, cs)

    //val s = new Scorer(sm, ds)

    //ds.foldRight(dt)(e => e._1.add(e._2))
    //val tv = new TopicVector(ds.head.id, ds.head.terms)

    //println(cs.sD)
    //println(s.score(tv))

    println("ciao")
    println(ds.toString)
    Out.writeTextFile("./IDF.txt", ds.toString)
  }

  def addVector(tv:TermsVector, acc:MutableTermsColumn): MutableTermsColumn = new MutableTermsColumn({
      tv.mTerms.map(v => acc.uTerms.put(v._1, (acc.uTerms.getOrElse(v._1, 0d) + 1d)))
      acc.uTerms
    })

  def time[R](block: => R): R = {
    val t0 = System.nanoTime()
    val result = block // call-by-name
    val t1 = System.nanoTime()
    println("Elapsed time: " + (t1 - t0) / 1000000000 + "s")
    result
  }


  def printlnWithTime[R](message:String, block: => R): R = {
    val t0 = System.nanoTime()
    val result = block    // call-by-name
    val t1 = System.nanoTime()
    print(message)
    println(" - elapsed time: " + (t1 - t0)/1000 + "μs")
    result
  }
}