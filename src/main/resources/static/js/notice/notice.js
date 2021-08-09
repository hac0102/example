console.log("aaaa notice.js");


let notice = {
    init: function(){
        let _this = this;

        _this.makeNoticeGrid();
        _this.makeNoticePagination();
    },

    makeNoticeGrid: function(){
        const grid = new tui.Grid({
            el: document.getElementById('noticeGrid'),
            data: noticeData.list,
            scrollX: false,
            scrollY: false,
            columns: [
                {
                    header: 'No',
                    name: 'no',
                    hidden: true
                },
                {
                    header: 'Id',
                    name: 'id'
                },
                {
                    header: '제목',
                    name: 'title'
                },
                {
                    header: '내용',
                    name: 'content'
                },
                {
                    header: '등록날짜',
                    name: 'frstRegDate'
                }
            ]
        });
    },
    makeNoticePagination: function() {
        const Pagination = tui.Pagination;
        const container = document.getElementById('pagination');
        const options = { // below default value of options
            totalItems: noticeData.total,
            itemsPerPage: 10,
            visiblePages: 5,
            page: noticeData.pageNum,
            centerAlign: false,
            firstItemClassName: 'tui-first-child',
            lastItemClassName: 'tui-last-child',
            template: {
                page: '<a href="#" class="tui-page-btn">{{page}}</a>',
                currentPage: '<strong class="tui-page-btn tui-is-selected">{{page}}</strong>',
                moveButton:
                    '<a href="#" class="tui-page-btn tui-{{type}}">' +
                    '<span class="tui-ico-{{type}}">{{type}}</span>' +
                    '</a>',
                disabledMoveButton:
                    '<span class="tui-page-btn tui-is-disabled tui-{{type}}">' +
                    '<span class="tui-ico-{{type}}">{{type}}</span>' +
                    '</span>',
                moreButton:
                    '<a href="#" class="tui-page-btn tui-{{type}}-is-ellip">' +
                    '<span class="tui-ico-ellip">...</span>' +
                    '</a>'
            }
        };
        const pagination = new Pagination(container, options);

        pagination.on('beforeMove', function(e) {
            console.log("beforeMove :: ", e.page)
            // return false;
            window.document.location.assign("/notice?pageNum=" + e.page);
            // return true;
        });

        pagination.on('afterMove', function(e) {
            console.log("afterMove :: ", e.page)
            // return false;
            window.document.location.assign("/notice?pageNum=" + e.page);
            // return true;
        });
    }
}

notice.init();