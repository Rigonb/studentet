create table pagesat(
id serial primary key,
studentId int ,
dataEFillimit date,
dataEMbarimit date,
eshtePaguar boolean,
paguarMe timestamp not null default CURRENT_TIMESTAMP ,
  CONSTRAINT fk_student_pagesat
      FOREIGN KEY(studentId)
        REFERENCES studentet(id)
);