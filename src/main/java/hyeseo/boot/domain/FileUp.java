package hyeseo.boot.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Getter
@Table(name = "fileup")
public class FileUp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id")
    private long id;

    private String orgnm;
    private String savednm;
    private String savedpath;

    // Builder를 위한 private 생성자
    private FileUp(Builder builder) {
        this.orgnm = builder.orgnm;
        this.savednm = builder.savednm;
        this.savedpath = builder.savedpath;
    }

    // Builder 클래스 정의
    public static class Builder {
        private String orgnm;
        private String savednm;
        private String savedpath;

        public Builder orgnm(String orgnm) {
            this.orgnm = orgnm;
            return this;
        }

        public Builder savednm(String savednm) {
            this.savednm = savednm;
            return this;
        }

        public Builder savedpath(String savedpath) {
            this.savedpath = savedpath;
            return this;
        }

        public FileUp build() {
            return new FileUp(this);
        }
    }

    // builder() 메서드 수정
    public static Builder builder() {
        return new Builder();
    }
}
