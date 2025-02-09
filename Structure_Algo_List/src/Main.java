//TIP 要<b>运行</b>代码，请按 <shortcut actionId="Run"/> 或
// 点击装订区域中的 <icon src="AllIcons.Actions.Execute"/> 图标。
public class Main {
    public static void main(String[] args) {


        MyList list = new MyList();
        list.addBack("jajajaj");
        list.addBack("jajajaj22222");
        list.addBack("jajajaj3333333");
        String temp = (String)list.pop();
        while (temp != null) {
            System.out.println("  " + temp);
            temp = (String)list.pop();
        }

//        Animal test = new Dog("lueluelue");
//
//        System.out.println(test.getName());


    }
    private static class Dog extends Animal {

        private String name;

        public Dog(String name) {
            super(name);
            this.name = name;
        }

        @Override
        public String getName() {
            return name;
        }
        @Override
        public void setName(String name) {
            this.name = name;
        }
    }

    private static class Animal {
        private String name;

        public Animal(String name) {
            this.name = name;
        }

        public String getName() {
            return name + "jajaja";
        }

        public void setName(String name) {
            this.name = name + "jajaja";
        }
    }
}