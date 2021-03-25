package com.github.entropyfeng.apartment;


import com.github.entropyfeng.apartment.dao.CollegeDao;
import com.github.entropyfeng.apartment.dao.StudentDao;
import com.github.entropyfeng.apartment.domain.Gender;
import com.github.entropyfeng.apartment.domain.StudentAccountStatus;
import com.github.entropyfeng.apartment.domain.StudentStatus;
import com.github.entropyfeng.apartment.domain.po.College;
import com.github.entropyfeng.apartment.domain.po.Student;
import com.github.entropyfeng.apartment.service.CollegeService;
import com.github.entropyfeng.apartment.service.UniversityIdService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InitUniversityTest {

    @Autowired
    StudentDao studentDao;

    @Autowired
    CollegeDao collegeDao;

    @Autowired
    UniversityIdService idService;

    @Autowired
    CollegeService collegeService;

    private void clearAll() {
        idService.clearAll();
        collegeDao.truncateCollege();
        studentDao.truncateStudent();
    }

    private void initDepartment() {

        ArrayList<String> departmentNames = new ArrayList<>();
        departmentNames.add("物理学院");
        departmentNames.add("电器学院");
        departmentNames.add("数学学院");
        departmentNames.add("电子学院");
        departmentNames.add("马克思学院");
        departmentNames.add("文学院");
        departmentNames.add("航空学院");
        departmentNames.add("医学院");
        departmentNames.add("农学院");
        departmentNames.add("机械学院");
        departmentNames.add("计算机学院");
        departmentNames.add("软件学院");
        departmentNames.add("化学学院");
        departmentNames.add("能源学院");

        departmentNames.forEach(name -> collegeService.addNewCollege(name));
    }

    private void initStudent() {
        List<Integer> collegeIds = collegeDao.queryAllCollege().stream().map(College::getCollegeId).collect(Collectors.toList());

        int departmentSize = collegeIds.size();
        List<String> firstNames = generateFirstName();
        AtomicInteger atomicInteger = new AtomicInteger(10);
        ThreadLocalRandom random = ThreadLocalRandom.current();

        firstNames.forEach(first -> {
            if (random.nextInt(100)<1){
                firstNames.forEach(second -> {
                    String studentId = "S" + String.format("%09d", atomicInteger.getAndIncrement());
                    String studentName = first + second;
                    Student student=new Student();
                    student.setStudentId(studentId);
                    student.setCollegeId(random.nextInt(1,departmentSize-1));
                    student.setEmail(studentId+"@mock.com");
                    student.setPhone(studentId.substring(1));
                    student.setGender(random.nextBoolean()?Gender.MAN:Gender.WOMAN);
                    student.setRegisterYear(random.nextInt(2010,2020)+"");
                    student.setIdCardNumber(studentId+"china");
                    student.setStudentName(studentName);
                    student.setStudentAccountStatus(StudentAccountStatus.NOT_EXIST);
                    student.setStudentStatus(StudentStatus.REGISTERED);
                    studentDao.insertStudent(student);
                });
            }
        });


    }

    @Test
    public void test() {

        clearAll();
        initDepartment();
        initStudent();
    }


    private List<String> generateFirstName() {
        return new ArrayList<String>() {{
            add("赵");
            add("钱");
            add("孙");
            add("李");
            add("周");
            add("吴");
            add("郑");
            add("王");
            add("冯");
            add("陈");
            add("褚");
            add("卫");
            add("蒋");
            add("沈");
            add("韩");
            add("杨");
            add("朱");
            add("秦");
            add("尤");
            add("许");
            add("何");
            add("吕");
            add("施");
            add("张");
            add("孔");
            add("曹");
            add("严");
            add("华");
            add("金");
            add("魏");
            add("陶");
            add("姜");
            add("戚");
            add("谢");
            add("邹");
            add("喻");
            add("柏");
            add("水");
            add("窦");
            add("章");
            add("云");
            add("苏");
            add("潘");
            add("葛");
            add("奚");
            add("范");
            add("彭");
            add("郎");
            add("鲁");
            add("韦");
            add("昌");
            add("马");
            add("苗");
            add("凤");
            add("花");
            add("方");
            add("俞");
            add("任");
            add("袁");
            add("柳");
            add("酆");
            add("鲍");
            add("史");
            add("唐");

            add("费");
            add("廉");
            add("岑");
            add("薛");
            add("雷");
            add("贺");
            add("倪");
            add("汤");

            add("滕");
            add("殷");
            add("罗");
            add("毕");
            add("郝");
            add("邬");
            add("安");
            add("常");

            add("乐");
            add("于");
            add("时");
            add("傅");
            add("皮");
            add("卞");
            add("齐");
            add("康");
            add("伍");
            add("余");
            add("元");
            add("卜");
            add("顾");
            add("孟");
            add("平");
            add("黄");
            add("和");
            add("穆");
            add("萧");
            add("尹");
            add("姚");
            add("邵");
            add("湛");
            add("汪");
            add("祁");
            add("毛");
            add("禹");
            add("狄");
            add("米");
            add("贝");
            add("明");
            add("臧");
            add("计");
            add("伏");
            add("成");
            add("戴");
            add("谈");
            add("宋");
            add("茅");
            add("庞");
            add("熊");
            add("纪");
            add("舒");
            add("屈");
            add("项");
            add("祝");
            add("董");
            add("梁");
            add("杜");
            add("阮");
            add("蓝");
            add("闵");
            add("席");
            add("季");
            add("麻");
            add("强");
            add("贾");
            add("路");
            add("娄");
            add("危");
            add("江");
            add("童");
            add("颜");
            add("郭");
            add("梅");
            add("盛");
            add("林");
            add("刁");
            add("钟");
            add("徐");
            add("邱");
            add("骆");
            add("高");
            add("夏");
            add("蔡");
            add("田");
            add("樊");
            add("胡");
            add("凌");
            add("霍");
            add("虞");
            add("万");
            add("支");
            add("柯");
            add("昝");
            add("管");
            add("卢");
            add("莫");
            add("经");
            add("房");
            add("裘");
            add("缪");
            add("干");
            add("解");
            add("应");
            add("宗");
            add("丁");
            add("宣");
            add("贲");
            add("邓");
            add("郁");
            add("单");
            add("杭");
            add("洪");
            add("包");
            add("诸");
            add("左");
            add("石");
            add("崔");
            add("吉");
            add("钮");
            add("龚");
            add("程");
            add("嵇");
            add("邢");
            add("滑");
            add("裴");
            add("陆");
            add("荣");
            add("翁");
            add("荀");
            add("羊");
            add("於");
            add("惠");
            add("甄");
            add("曲");
            add("家");
            add("封");
            add("芮");
            add("羿");
            add("储");
            add("靳");
            add("汲");
            add("邴");
            add("糜");
            add("松");
            add("井");
            add("段");
            add("富");
            add("巫");
            add("乌");
            add("焦");
            add("巴");
            add("弓");
            add("牧");
            add("隗");
            add("山");
            add("谷");
            add("车");
            add("侯");
            add("宓");
            add("蓬");
            add("全");
            add("郗");
            add("班");
            add("仰");
            add("秋");
            add("仲");
            add("伊");
            add("宫");
            add("宁");
            add("仇");
            add("栾");
            add("暴");
            add("甘");
            add("钭");
            add("厉");
            add("戎");
            add("祖");
            add("武");
            add("符");
            add("刘");
            add("景");
            add("詹");
            add("束");
            add("龙");
            add("叶");
            add("幸");
            add("司");
            add("韶");
            add("郜");
            add("黎");
            add("蓟");
            add("薄");
            add("印");
            add("宿");
            add("白");
            add("怀");
            add("蒲");
            add("邰");
            add("从");
            add("鄂");
            add("索");
            add("咸");
            add("籍");
            add("赖");
            add("卓");
            add("蔺");
            add("屠");
            add("蒙");
            add("池");
            add("乔");
            add("阴");
            add("鬱");
            add("胥");
            add("能");
            add("苍");
            add("双");
            add("闻");
            add("莘");
            add("党");
            add("翟");
            add("谭");
            add("贡");
            add("劳");
            add("逄");
            add("姬");
            add("申");
            add("扶");
            add("堵");
            add("冉");
            add("宰");
            add("郦");
            add("雍");
            add("卻");
            add("璩");
            add("桑");
            add("桂");
            add("濮");
            add("牛");
            add("寿");
            add("通");
            add("边");
            add("扈");
            add("燕");
            add("冀");
            add("郏");
            add("浦");
            add("尚");
            add("农");
            add("温");
            add("别");
            add("庄");
            add("晏");
            add("柴");
            add("瞿");
            add("阎");
            add("充");
            add("慕");
            add("连");
            add("茹");
            add("习");
            add("宦");
            add("艾");
            add("鱼");
            add("容");
            add("向");
            add("古");
            add("易");
            add("慎");
            add("戈");
            add("廖");
            add("庾");
            add("终");

            add("暨");
            add("居");
            add("衡");
            add("步");
            add("都");
            add("耿");
            add("满");
            add("弘");

            add("匡");
            add("国");
            add("文");
            add("寇");
            add("广");
            add("禄");
            add("阙");
            add("东");

            add("欧");
            add("殳");
            add("沃");
            add("利");
            add("蔚");
            add("越");
            add("夔");
            add("隆");

            add("师");
            add("巩");
            add("厍");
            add("聂");
            add("晁");
            add("勾");
            add("敖");
            add("融");

            add("冷");
            add("訾");
            add("辛");
            add("阚");
            add("那");
            add("简");
            add("饶");
            add("空");

            add("曾");
            add("毋");
            add("沙");
            add("乜");
            add("养");
            add("鞠");
            add("须");
            add("丰");

            add("巢");
            add("关");
            add("蒯");
            add("相");
            add("查");
            add("后");
            add("荆");
            add("红");

            add("游");
            add("竺");
            add("权");
            add("逯");
            add("盖");
            add("益");
            add("桓");
            add("公");

            add("万");
            add("俟");
            add("司");
            add("马");
            add("上");
            add("官");
            add("欧");
            add("阳");

            add("夏");
            add("侯");
            add("诸");
            add("葛");
            add("闻");
            add("人");
            add("东");
            add("方");

            add("赫");
            add("连");
            add("皇");
            add("甫");
            add("尉");
            add("迟");
            add("公");
            add("羊");

            add("澹");
            add("台");
            add("公");
            add("冶");
            add("宗");
            add("政");
            add("濮");
            add("阳");

            add("淳");
            add("于");
            add("单");
            add("于");
            add("太");
            add("叔");
            add("申");
            add("屠");

            add("公");
            add("孙");
            add("仲");
            add("孙");
            add("轩");
            add("辕");
            add("令");
            add("狐");

            add("钟");
            add("离");
            add("宇");
            add("文");
            add("长");
            add("孙");
            add("慕");
            add("容");

            add("鲜");
            add("于");
            add("闾");
            add("丘");
            add("司");
            add("徒");
            add("司");
            add("空");

            add("丌");
            add("官");
            add("司");
            add("寇");
            add("仉");
            add("督");
            add("子");
            add("车");

            add("颛");
            add("孙");
            add("端");
            add("木");
            add("巫");
            add("马");
            add("公");
            add("西");

            add("漆");
            add("雕");
            add("乐");
            add("正");
            add("壤");
            add("驷");
            add("公");
            add("良");

            add("拓");
            add("跋");
            add("夹");
            add("谷");
            add("宰");
            add("父");
            add("谷");
            add("梁");

            add("晋");
            add("楚");
            add("闫");
            add("法");
            add("汝");
            add("鄢");
            add("涂");
            add("钦");

            add("段");
            add("干");
            add("百");
            add("里");
            add("东");
            add("郭");
            add("南");
            add("门");

            add("呼");
            add("延");
            add("归");
            add("海");
            add("羊");
            add("舌");
            add("微");
            add("生");

            add("岳");
            add("帅");
            add("缑");
            add("亢");
            add("况");
            add("郈");
            add("有");
            add("琴");

            add("梁");
            add("丘");
            add("左");
            add("丘");
            add("东");
            add("门");
            add("西");
            add("门");

            add("商");
            add("牟");
            add("佘");
            add("佴");
            add("伯");
            add("赏");
            add("南");
            add("宫");

            add("墨");
            add("哈");
            add("谯");
            add("笪");
            add("年");
            add("爱");
            add("阳");
            add("佟");

            add("第");
            add("五");
            add("言");
            add("福");
            add("百");
            add("家");
            add("姓");
            add("终");

        }};
    }


}
