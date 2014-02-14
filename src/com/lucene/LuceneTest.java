package com.lucene;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.LockObtainFailedException;
import org.apache.lucene.util.Version;

public class LuceneTest {

    private String basePath = "L:/workspace/Test/src/com/lucene/";

    /*
     * 创建索引
     */
    public void index() {
        IndexWriter writer = null;

        try {
            //1、创建directory
            Directory directory = FSDirectory.open(new File(basePath + "/Index01"));
            // Directory directory=new RAMDirectory();//建立在内存中

            //2、创建IndexWriter
            IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_35, new StandardAnalyzer(Version.LUCENE_35));
            writer = new IndexWriter(directory, iwc);

            //3、创建document对象
            Document doc = null;
            
            //4、为document添加field
            File f = new File(basePath + "lucenes");
            for (File file : f.listFiles()) {
                doc = new Document();
                doc.add(new Field("content", new FileReader(file)));
                doc.add(new Field("filename", file.getName(), Field.Store.YES, Field.Index.NOT_ANALYZED));
                doc.add(new Field("path", file.getAbsolutePath(), Field.Store.YES, Field.Index.NOT_ANALYZED));
                //5、通过IndexWriter将document添加到索引中
                writer.addDocument(doc);
            }

        }
        catch (CorruptIndexException e) {
            e.printStackTrace();
        }
        catch (LockObtainFailedException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            //6、关闭writer
            try {
                if (writer != null) {
                    writer.close();
                }
            }
            catch (CorruptIndexException e2) {
                e2.printStackTrace();
            }
            catch (IOException e2) {
                e2.printStackTrace();
            }
        }
    }

    /*
     * 查找内容
     */
    public void serch() {
        IndexReader reader = null;
        try {
            //1、创建Directory
            Directory directory = FSDirectory.open(new File(basePath + "Index01"));

            //2、创建IndexReader
            reader = IndexReader.open(directory);

            //3、根据IndexReader创建IndexSercher
            IndexSearcher sercher = new IndexSearcher(reader);

            //4、创建搜索的Quary
            //创建parse来确定搜索文件的内容,第二个参数是搜索的域
            QueryParser parser = new QueryParser(Version.LUCENE_35, "content", new StandardAnalyzer(Version.LUCENE_35));
            //创建quary，表示搜索域为content包含name的内容
            Query quary = parser.parse("ask");

            //5、根据色弱sercher搜索并返回TopDocs
            TopDocs tds = sercher.search(quary, 10);//10表示显示10条搜索结果

            //6、根据TopDocs获取ScoreDoc对象
            ScoreDoc[] sds = tds.scoreDocs;
            for (ScoreDoc sd : sds) {
                //7、根据sercher对象和Scoredoc对象获取Document对象
                Document d = sercher.doc(sd.doc);
                //8、根据Document对象获取需要的值
                System.out.println(d.get("filename") + "[" + d.get("path") + "]");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            //9、关闭reader
            try {
                if (reader != null) {
                    reader.close();
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new LuceneTest().index();
        new LuceneTest().serch();
    }

}
