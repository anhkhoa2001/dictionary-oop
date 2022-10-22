# dictionary_FX_notMV

Bài tập lớn môn Lập trình hướng đối tượng
Xây dựng chương trình từ điển bằng Java

Ghi chú: Trong quá trình thực hành, chương trình được phép bổ sung thêm lớp và hàm cần thiết, dữ liệu đầu vào và đầu ra của mỗi hàm.
Chương trình có thể sử dụng thêm các thư viện java bên ngoài nếu cần
Các phiên bản cần cài đặt
1. Phiên bản từ điển command line sơ khai: ................................................................................................ 1
2. Phiên bản từ điển command line cải tiến lần 1: ....................................................................................... 2
3. Phiên bản từ điển command line cải tiến lần 2: ....................................................................................... 2
4. Phiên bản từ điển giao diện đồ họa sơ khai ............................................................................................. 3
5. Phiên bản từ điển giao diện đồ họa nâng cao .......................................................................................... 3
6. Phiên bản từ điển giao diện đồ họa cao cấp............................................................................................. 3
Chấm điểm: ................................................................................................................................................... 5

1. Phiên bản từ điển command line sơ khai:

Xây dựng chương trình nhập dữ liệu từ điển Anh – Việt.
- Tạo class Word có thuộc tính word_target (từ mới), word_explain (giải nghĩa)
- Tạo class Dictionary lưu trữ mảng Word
- Xây dựng class DictionaryManagement có hàm insertFromCommandline()
- Hàm insertFromCommandline() có chức năng nhập liệu:
Nhập vào bàn phím số lượng từ vựng (Word).
Format nhập dữ liệu từ điển Anh – Việt
Dòng 1: Nhập từ tiếng Anh
Dòng 2: Nhập giải thích sang tiếng Việt
- Xây dựng class DictionaryCommandline có hàm showAllWords()
- Hàm showAllWords() có chức năng hiển thị kết quả danh sách dữ liệu từ điển trên màn hình ví dụ:

No | English | Vietnamese
1 | Hello | Xin chao
2 | House | Ngoi nha
...
- Xây dựng class DictionaryCommandLine có hàm dictionaryBasic()
- Hàm dictionaryBasic() có chức năng gọi hàm insertFromCommandline() và showAllWords()
2. Phiên bản từ điển command line cải tiến lần 1:
Tiếp tục phát triển chương trình từ điển Anh – Việt
- Trong class DictionaryManagement bổ sung hàm insertFromFile()
- Hàm insertFromFile() nhập dữ liệu từ điển từ tệp dictionaries.txt
Quy định files: dictionaries.txt: Mỗi dòng chứa từ tiếng anh và giải thích tiếng việt (ngăn cách nhau bằng dấu tab)
- In kết quả danh sách từ điển như bài trước
- Trong class DictionaryManagementbổ sung hàm dictionaryLookup() có chức năng tra cứu từ điển bằng dòng lệnh
- Trong class DictionaryCommandLine bổ sung hàm dictionaryAdvanced() có chức năng gọi hàm insertFromFile(), showAllWords() và dictionaryLookup()
3. Phiên bản từ điển command line cải tiến lần 2:
Tiếp tục phát triển chương trình từ điển Anh – Việt
- Trong class DictionaryManagement phát triển thêm các hàm có chức năng thêm sửa xóa dữ liệu từ điển bằng dòng lệnh
- Trong class DictionaryCommandLine bổ sung hàm dictionarySearcher() có chức năng tìm kiếm các từ
Ví dụ nhập: tra
Kết quả trả về: danh sách các từ vựng bắt đầu bằng “tra”: transport, translate, transform, transit, ...
- Trong class DictionaryManagement bổ sung hàm dictionaryExportToFile() có chức năng xuất dữ liệu từ điển hiện tại ra files

4. Phiên bản từ điển giao diện đồ họa sơ khai
Tiếp tục phát triển chương trình từ điển Anh – Việt
- Xây dựng class DictionaryApplication có hàm runApplication(). Hàm runApplication() có chức năng hiển thị giao diện đồ họa
- Yêu cầu của giao diện đồ họa
Khi khởi động chương trình, chương trình sẽ nạp dữ liệu dictionaries.txt
Chương trình hiển thị 2 phần: phần 1 là danh sách từ, phần 2 là giải thích từ vựng của từ tiếng anh đó
Thêm các hàm và lớp hỗ trợ để hiển thị phần giải thích từ vựng
Tham khảo giao diện mẫu

5. Phiên bản từ điển giao diện đồ họa nâng cao
Tiếp tục phát triển chương trình từ điển Anh – Việt
- Thêm các chức năng nhập thêm từ mới
- Thêm chức năng xóa sửa từ vựng
- Tích hợp chức năng tra từ trong hàm dictionarySearcher
- Tích hợp chức năng phát âm tiếng anh
6. Phiên bản từ điển giao diện đồ họa cao cấp
Yêu cầu Không bằng buộc. Bài tập khuyến khích sáng tạo

- Tối ưu thuật toán tra từ
- Sử dụng dữ liệu từ điển Anh – Việt trên mạng bằng mysql, sqlite, ... thay vì nạp từ dictionaries.txt ( dữ liệu từ điển có thể bao gồm: giải thích bằng tiếng Việt, từ
đồng nghĩa, trái nghĩa, ví dụ, phát âm Anh Mỹ và Anh Anh...)
- Tích hợp kết quả tra từ, đoạn văn bằng API google translate
- Cải tiến giao diện chương trình thân thiện đẹp mắt hơn

Công nghệ: JavaFX
