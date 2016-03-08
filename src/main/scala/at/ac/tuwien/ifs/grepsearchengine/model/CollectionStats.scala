package at.ac.tuwien.ifs.grepsearchengine.model

import at.ac.tuwien.ifs.grepsearchengine.model.index.{SCDt, CDt, DocumentVector}

/**
 * Created by aldo on 29/02/16.
 */
class CollectionStats(val docs:List[DocumentVector], val sCDt:SCDt) {

  lazy val sT = sCDt.sdts.size

  lazy val sD = docs.size

  lazy val lc = docs.map(_.ld).sum

  lazy val avgld = lc/sD

}
