--connect 'jdbc:derby://localhost:1527/BOOK;create=true;user=BOOK;password=book';
create table livros (id integer not null,
titulo varchar(100) not null);

select * from livros ;