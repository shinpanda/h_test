import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

import java.io.InputStream;
import java.net.URI;

public class FileSystemCat{
    public static void main(String[] args)  throws Exception {
        String uri = args[0];
        Configuration conf = new Configuration();
        // 클라이언트나 서버의 환경 설정 포함.
        // core-site.xml과 같은 설정 파일에서 관련 설정을 읽어 들인다.
        FileSystem fs = FileSystem.get(URI.create(uri), conf);
        // 주어진 URI 스킴과 권한으로 파일시스템을 결정하며, URI에 스킴을 명시하지 않으면 기본 파일시스템으로 간주한다.
        InputStream in = null;
        try{
            in = fs.open(new Path(uri)); // 파일에 대한 입력 스트림을 엶 4KB의 기본 버퍼 크기를 사용.
            IOUtils.copyBytes(in, System.out, 4096, false);
        } finally {
            IOUtils.closeStream(in);
        }
    }
}
