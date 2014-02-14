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
     * ��������
     */
    public void index() {
        IndexWriter writer = null;

        try {
            //1������directory
            Directory directory = FSDirectory.open(new File(basePath + "/Index01"));
            // Directory directory=new RAMDirectory();//�������ڴ���

            //2������IndexWriter
            IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_35, new StandardAnalyzer(Version.LUCENE_35));
            writer = new IndexWriter(directory, iwc);

            //3������document����
            Document doc = null;
            
            //4��Ϊdocument���field
            File f = new File(basePath + "lucenes");
            for (File file : f.listFiles()) {
                doc = new Document();
                doc.add(new Field("content", new FileReader(file)));
                doc.add(new Field("filename", file.getName(), Field.Store.YES, Field.Index.NOT_ANALYZED));
                doc.add(new Field("path", file.getAbsolutePath(), Field.Store.YES, Field.Index.NOT_ANALYZED));
                //5��ͨ��IndexWriter��document��ӵ�������
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
            //6���ر�writer
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
     * ��������
     */
    public void serch() {
        IndexReader reader = null;
        try {
            //1������Directory
            Directory directory = FSDirectory.open(new File(basePath + "Index01"));

            //2������IndexReader
            reader = IndexReader.open(directory);

            //3������IndexReader����IndexSercher
            IndexSearcher sercher = new IndexSearcher(reader);

            //4������������Quary
            //����parse��ȷ�������ļ�������,�ڶ�����������������
            QueryParser parser = new QueryParser(Version.LUCENE_35, "content", new StandardAnalyzer(Version.LUCENE_35));
            //����quary����ʾ������Ϊcontent����name������
            Query quary = parser.parse("ask");

            //5������ɫ��sercher����������TopDocs
            TopDocs tds = sercher.search(quary, 10);//10��ʾ��ʾ10���������

            //6������TopDocs��ȡScoreDoc����
            ScoreDoc[] sds = tds.scoreDocs;
            for (ScoreDoc sd : sds) {
                //7������sercher�����Scoredoc�����ȡDocument����
                Document d = sercher.doc(sd.doc);
                //8������Document�����ȡ��Ҫ��ֵ
                System.out.println(d.get("filename") + "[" + d.get("path") + "]");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            //9���ر�reader
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
