/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finsys;

/**
 *
 * @author 320
 */
public class Stockouttable {
    
    private String issue_returncode ,acc_post,issuedate,receiptno,issue_or_return ;
    int costcenterid  ;
    private Double issueamt_value,transportation_amt ;

    public Stockouttable(String issue_returncode, String acc_post, String issuedate, String receiptno, String issue_or_return, int costcenterid, Double issueamt_value, Double transportation_amt) {
        this.issue_returncode = issue_returncode;
        this.acc_post = acc_post;
        this.issuedate = issuedate;
        this.receiptno = receiptno;
        this.issue_or_return = issue_or_return;
        this.costcenterid = costcenterid;
        this.issueamt_value = issueamt_value;
        this.transportation_amt = transportation_amt;
    }

    public Stockouttable() {
        
        this.issue_returncode = "";
        this.acc_post = "";
        this.issuedate = "";
        this.receiptno = "";
        this.issue_or_return = "";
        this.costcenterid = 0;
        this.issueamt_value = 0.0;
        this.transportation_amt = 0.0;
    }

    public String getIssue_returncode() {
        return issue_returncode;
    }

    public void setIssue_returncode(String issue_returncode) {
        this.issue_returncode = issue_returncode;
    }

    public String getAcc_post() {
        return acc_post;
    }

    public void setAcc_post(String acc_post) {
        this.acc_post = acc_post;
    }

    public String getIssuedate() {
        return issuedate;
    }

    public void setIssuedate(String issuedate) {
        this.issuedate = issuedate;
    }

    public String getReceiptno() {
        return receiptno;
    }

    public void setReceiptno(String receiptno) {
        this.receiptno = receiptno;
    }

    public String getIssue_or_return() {
        return issue_or_return;
    }

    public void setIssue_or_return(String issue_or_return) {
        this.issue_or_return = issue_or_return;
    }

    public int getCostcenterid() {
        return costcenterid;
    }

    public void setCostcenterid(int costcenterid) {
        this.costcenterid = costcenterid;
    }

    public Double getIssueamt_value() {
        return issueamt_value;
    }

    public void setIssueamt_value(Double issueamt_value) {
        this.issueamt_value = issueamt_value;
    }

    public Double getTransportation_amt() {
        return transportation_amt;
    }

    public void setTransportation_amt(Double transportation_amt) {
        this.transportation_amt = transportation_amt;
    }
    
    
    
    
   
    
}
