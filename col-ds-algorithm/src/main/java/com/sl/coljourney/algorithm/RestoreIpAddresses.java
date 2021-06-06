package com.sl.coljourney.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * https://www.nowcoder.com/practice/ce73540d47374dbe85b3125f57727e1e?tpId=117&&tqId=37725&&companyId=665&rp=1&ru=/company/home/code/665&qru=/ta/job-code-high/question-ranking
 * https://leetcode-cn.com/problems/restore-ip-addresses/
 * <p>
 * 93. 复原 IP 地址
 * 难度：中等
 * 给定一个只包含数字的字符串，用以表示一个 IP 地址，返回所有可能从 s 获得的 有效 IP 地址 。你可以按任何顺序返回答案。
 * 有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
 * <p>
 * 例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是 无效 IP 地址。
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "25525511135"
 * 输出：["255.255.11.135","255.255.111.35"]
 * <p>
 * 示例 2：
 * <p>
 * 输入：s = "0000"
 * 输出：["0.0.0.0"]
 * <p>
 * 示例 3：
 * <p>
 * 输入：s = "1111"
 * 输出：["1.1.1.1"]
 * <p>
 * 示例 4：
 * <p>
 * 输入：s = "010010"
 * 输出：["0.10.0.10","0.100.1.0"]
 * <p>
 * 示例 5：
 * <p>
 * 输入：s = "101023"
 * 输出：["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
 */
public class RestoreIpAddresses {

    public List<String> restoreIpAddresses(String s) {
        ArrayList<String> ans = new ArrayList<>();
        if (s == null || "".equals(s) || s.length() < 4 || s.length() > 12) {
            return ans;
        }

        reverseIp(s, "", 0, ans);
        return ans;
    }

    private void reverseIp(String s, String ip, int segement, List<String> ans) {
        if (s.length() <= 0) {
            return;
        }
        if (segement == 3) {
            if (s.length() > 3) {
                return;
            }
            if (s.length() > 1 && s.startsWith("0")) {
                return;
            }
            int cur = Integer.parseInt(s);
            if (cur > 255) {
                return;
            }
            ip += s;
            ans.add(ip);
            return;
        }

        String pre1 = s.substring(0, 1);
        String after1 = s.substring(1);
        reverseIp(after1, ip + pre1 + ".", segement + 1, ans);

        if (s.length() > 2) {
            String pre2 = s.substring(0, 2);
            String after2 = s.substring(2);
            if (!pre2.startsWith("0") && Integer.parseInt(pre2) < 256) {
                reverseIp(after2, ip + pre2 + ".", segement + 1, ans);
            }
        }

        if (s.length() > 3) {
            String pre3 = s.substring(0, 3);
            String after3 = s.substring(3);
            if (!pre3.startsWith("0") && Integer.parseInt(pre3) < 256) {
                reverseIp(after3, ip + pre3 + ".", segement + 1, ans);
            }
        }
    }
}
