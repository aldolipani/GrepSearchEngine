package at.ac.tuwien.ifs.grepsearchengine.model.input

import java.io.File

/**
 * Created by aldo on 26/02/16.
 */
class Collection(val rootDir:File) {

  lazy val documents:List[Document] = Collection.scanRDir(rootDir)

}

object Collection{

  def scanRDir(dir:File, ldir:List[File] = Nil):List[Document] = {
    val nldir = dir.listFiles().filter(d => d.isDirectory && !d.isHidden).toList
    val uldir = ldir ::: nldir
    dir.listFiles().filter(d => d.isFile && !d.isHidden)
      .map(f => new Document(f.getName, f.getCanonicalPath)).toList:::(
      if(uldir.nonEmpty)
        scanRDir(uldir.head, uldir.tail)
      else
        Nil)
  }



}