package com.sl.coljourney.datastructure.stack;

import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;

/**
 * 71. 简化路径  难度：中等
 * https://leetcode-cn.com/problems/simplify-path/
 *
 * @author L
 */
public class SimplifyPath {


    /**
     * 首先排除干扰的 / ，这个问题就好理解多了。
     * 输入的路径分为3种，第一种：. ，代表当前目录；第二种：..，代表父目录；第三种：正常路径
     * 1. 将输入的路径按照 / 分割。
     * 2. 分割后就剩下空字符串，.，..，正常路径4中情况。
     * 3. 空字符串 和 . 可直接忽略。因为空字符串就是路径前或多个/之间的没有实际意义，.代表当前路径。
     * 4. 利用栈结构，将正常路径压栈。
     * 5. 如果是..，则表示其父目录，弹出栈顶路径。
     * 6. 最后，如果栈为空，则返回/；否则，则按照路径拼接。
     *
     * @param path 输入路径
     * @return 最终实际路径
     */
    public String simplifyPath(String path) {
        if (path == null || path.length() < 1) {
            return "";
        }

        Stack<String> stack = new Stack<>();
        String[] pathArr = path.split("/");
        for (String p : pathArr) {
            if (".".equals(p) || p.length() < 1) {
                continue;
            }
            if ("..".equals(p)) {
                if (!stack.empty()) {
                    stack.pop();
                }
            } else {
                stack.push(p);
            }
        }
        if (stack.empty()) {
            return "/";
        }
        StringBuilder fPath = new StringBuilder();
        for (String p : stack) {
            fPath.append("/").append(p);
        }
        return fPath.toString();
    }

    @Test
    public void simplifyPathTest() {

        SimplifyPath simplifyPath = new SimplifyPath();
        Assert.assertEquals("/home", simplifyPath.simplifyPath("/home/"));
        Assert.assertEquals("/", simplifyPath.simplifyPath("/../"));
        Assert.assertEquals("/home/foo", simplifyPath.simplifyPath("/home//foo/"));
        Assert.assertEquals("/c", simplifyPath.simplifyPath("/a/./b/../../c"));
        Assert.assertEquals("/c", simplifyPath.simplifyPath("/a/../../b/../c//.//"));
        Assert.assertEquals("/a/b/c", simplifyPath.simplifyPath("/a//b////c/d//././/.."));

    }

}
