<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div id="menuConfirmModal" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" style="color: red">警告!!!</h4>
            </div>
            <form>
                <div class="modal-body">
                    确定要删除<span id="removeNodeSpan"></span>这个节点吗？
                </div>
                <div class="modal-footer">
                    <button id="confirmBtn" type="button" class="btn btn-danger"><i class="glyphicon glyphicon-ok"></i> 确定</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">算了</button>

                </div>
            </form>
        </div>
    </div>
</div>